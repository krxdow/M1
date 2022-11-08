//
// Created by ludow on 05/11/22.
//

#include <errno.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

#include <sys/socket.h>

#include <pthread.h>

int  tcp_server(const char * service_name,char* node);//note faire passer en argument les socket ou faire une fonction qui connect les fonction
int  quit_server(void);
void handle_connection(int sock);
typedef struct {
    char *service_name; //port
   // char* name_server; //name of server
}node_server;


int tcp_server (const char * service_name, char* node)
{

    char* name_server=node;
    int err;
    int server_socket;
    int connected_socket;
    struct addrinfo  hints;
    struct addrinfo *results;
    struct sockaddr_in * addr_in;
    socklen_t length = 0;
    char hostname [NI_MAXHOST];
    char servname [NI_MAXSERV];

    // Creer la socket serveur et lui attribuer un numero de service.
    if ((server_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0) {
        perror("socket");
        return -1;
    }

    //mise à zero l'espase memoire de la structure
    memset(&hints, 0, sizeof(hints));

    //configuration de l'ip
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_flags = AI_PASSIVE;

    //getaddrinfo fourni une structure sockaddr retourner dans results
    //permet de tester la configuration de lip et configurer le port
    if ((err = getaddrinfo(NULL, service_name,  &hints, &results)) != 0) {
        fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(err));
        close(server_socket);
        return -1;
    }

    //affectation d'une identité à la socket
    err = bind(server_socket, results->ai_addr, results->ai_addrlen);
    freeaddrinfo(results);
    if (err < 0) {
        perror("bind");
        close(server_socket);
        return -1;
    }

    // Afficher notre adresse locale.
    fprintf(stdout, "Mon adresse >> ");
    length = sizeof(struct sockaddr_in);
    if (getsockname(server_socket, (struct sockaddr *) &addr_in, &length) < 0) {
        perror ("getsockname");
        return -1;
    }
    if (getnameinfo((struct sockaddr *) &addr_in, length,
                    hostname, NI_MAXHOST,
                    servname, NI_MAXSERV,
                    NI_NUMERICHOST | NI_NUMERICSERV) == 0)
        fprintf (stdout, "IP = %s, Port = %s \n", hostname, servname);

    int value;
    value=1;
    setsockopt(server_socket,SOL_SOCKET,SO_REUSEADDR,
    &value,sizeof(value));


    //indique au noyau que nous attendons des connections sur cette socket
    //avec une file d'attente de 5, si une demande de connection est arriver est que le serveur est occuper elle sera mise en attente,
    // exemple deux connection simultanée
    listen(server_socket, 5);

    while (! quit_server()) {
        connected_socket = accept(server_socket, NULL, NULL);
        if (connected_socket < 0) {
            perror("accept");
            return -1;
        }


      /*  switch (fork()) {
            case 0 : // enfant
                close(server_socket);
                handle_connection(connected_socket);
                exit(EXIT_SUCCESS);
            case -1 :
                perror("fork");
                return -1;
            default : // parent
                close(connected_socket);
        }*/
    }
    return 0;
}
int quit_server (void)
{
    pthread_exit(NULL);
    return 0;
}
void handle_connection (int sock)
{
    struct sockaddr * sockaddr;
    socklen_t length = 0;
    char hostname [NI_MAXHOST];
    char servname [NI_MAXSERV];
    char buffer [256];

    // Afficher l'adresse du correspondant
    getpeername(sock, NULL, &length);
    if (length == 0)
        return;
    sockaddr = malloc(length);
    if (getpeername(sock, sockaddr, & length) < 0) {
        perror ("getpeername");
        free(sockaddr);
        return;
    }
    if (getnameinfo(sockaddr, length,
                    hostname, NI_MAXHOST,
                    servname, NI_MAXSERV,
                    NI_NUMERICHOST | NI_NUMERICSERV) == 0) {
        snprintf (buffer, 256, "IP = %s, Port = %s \n", hostname, servname);
        fprintf(stdout, "Connexion distante %s", buffer);
        write(sock, "Votre adresse : ", 16);
        write(sock, buffer, strlen(buffer));
    }
    free(sockaddr);
    close(sock);
}



void * thread_srv(void *arg){


    node_server *node = (node_server *)  arg;
    char * service_name= strdup(node->service_name);

   //printf("%s",service_name);

      int ret=tcp_server(service_name,"node");
    printf("return %d",ret);

    pthread_exit(NULL);

}



int main (int argc, char * argv[])
{
    pthread_t thread[200];

    node_server  * node=&(node_server){.service_name="00000"};

    int privat_port=49152;
    char * str_int;
    int err;

    void *ptr;

    for (int i = 0; i < sizeof(thread)/sizeof(thread[0]) ; ++i) {

        int service_name=privat_port+i;// port incrementer pour chaque server creer

        char *number_node,name_port[]="00000";// 4 for number of digit port

        sprintf(name_port,"%d",service_name);
        //printf(" %s",name_port);

        node->service_name= strdup(name_port);


        if ((err= pthread_create(&thread[i],NULL,thread_srv,node))!=0){
            fprintf(stderr,"%s", strerror(err));
        }

        if ((err=  pthread_join(thread[i],ptr))!=0){
            fprintf(stderr,"%s", strerror(err));
        }
        //pthread_detach(thread[i]);
    }
    
    

}