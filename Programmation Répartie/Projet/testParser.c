#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef __unix__
typedef long ssize_t;
#endif

// Note on: ssize_t getline(char** pline, size_t* plsize, FILE* fp)
// - `*pline` should be `realloc(*pline, newsize)` frendly pointer value
// - `*pline` should call `free(*pline)` for cleanup even if after `feof(fp)`
// - result value: -1 (eof/error) or > 0 (no empty string "")

// pass caller to process when failed realloc on both C99 or C89 behaviors
static inline int
alloc_line(char** restrict pline, size_t* restrict plsize, size_t newsize) {
    void* n = realloc(*pline, newsize);
    if (n == NULL) return -1;
    *pline = n;
    *plsize = newsize;
    return 0;
}

// implement POSIX getline() with fgets()
static ssize_t getline1(char** restrict pline, size_t* restrict plsize, FILE* restrict fp) {
    //if (*plsize < 128) if (alloc_line(pline, plsize, 128)) return -1;
    if (*plsize < 2) if (alloc_line(pline, plsize, 2)) return -1;
    if (*pline == NULL) return -1;
    if (feof(fp)) return -1;
    char* cur = *pline;
    size_t cursize = *plsize;
    for (;;) {
        char* ret = fgets(cur, cursize, fp);
        if (ret == NULL && !feof(fp)) return -1; //=> read error
        if (ret == NULL && cur == *pline) return -1; //=> read empty
        char* eod = memchr(cur, '\0', cursize);
        if (feof(fp) || eod[-1] == '\n') return eod - *pline;
        // line continued
        cursize = *plsize + 1; // last of *pline is '\0'
        if (alloc_line(pline, plsize, *plsize * 2)) return -1;
        cur = *pline + *plsize - cursize;
    }
}

// implement POSIX getline() with fgetc()
static ssize_t
getline2(char** restrict pline, size_t* restrict plsize, FILE* restrict fp) {
    //if (*plsize < 128) if (alloc_line(pline, plsize, 128)) return -1;
    if (*plsize < 2) if (alloc_line(pline, plsize, 2)) return -1;
    if (*pline == NULL) return -1;
    if (feof(fp)) return -1;
    ssize_t len = 0;
    for (int ch = fgetc(fp); ch != EOF; ch = fgetc(fp)) {
        (*pline)[len++] = ch;
        if ((size_t) len == *plsize) {
            if (alloc_line(pline, plsize, *plsize * 2)) return -1;
        }
        if (ch == '\n') break;
    }
    if (len == 0) return -1;
    (*pline)[len] = '\0';
    return len;
}

// return position of sub string and store verticesValue and edgeValue
ssize_t  scearchDef(char * def,FILE* fp,int *verticesPtr,int *edgesPtr){

    char* line = NULL;
    size_t linesize = 0;
    ssize_t len = 0; // getline return
    size_t n = 0;

    int vertices, edges;

    while ((len = getline1(&line, &linesize, fp)) != -1) {
        // printf("%5zu: %s", ++n, line);
        // printf("%zu:\n", len);


        char *pch = strstr(line, def);

        if (pch){
            //  printf("%5zu: %s", ++n, line);

            //first token
            char * strToken = strtok ( line, " ");

            //skip edge word
            strtok(NULL," ");


            sscanf(strtok(NULL," "),"%d",&vertices);
            sscanf(strtok(NULL," "),"%d",&edges);


          // printf("function %d %d\n",vertices,edges);

            *verticesPtr=vertices;
            *edgesPtr=edges;


            //printf("value ptr %d %d\n",verticesPtr,edgesPtr);
            break;
        }else if (pch=NULL)
            printf("\ndefinition du graphe non trouvé");
    }

    if (errno != ENOMEM) free(line);
    fclose(fp);



}


// example
int main(int argc, char** argv) {



    char *definition="p edge";


    for (int i = 1; i < argc; i++) {
        FILE* fp = fopen(argv[i], "r");
        if (fp == NULL) continue;



        int verticesValue;
        int  edgesValue;

        scearchDef(definition,fp,&verticesValue,&edgesValue);
        printf("value vertices %d\nvalue edges %d \n",verticesValue,edgesValue);


    }
    return 0;
}