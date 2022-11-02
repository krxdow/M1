
package web.service.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="intA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="intB" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "intA",
    "intB"
})
@XmlRootElement(name = "Divide")
public class Divide {

    protected int intA;
    protected int intB;

    /**
     * Obtient la valeur de la propriété intA.
     * 
     */
    public int getIntA() {
        return intA;
    }

    /**
     * Définit la valeur de la propriété intA.
     * 
     */
    public void setIntA(int value) {
        this.intA = value;
    }

    /**
     * Obtient la valeur de la propriété intB.
     * 
     */
    public int getIntB() {
        return intB;
    }

    /**
     * Définit la valeur de la propriété intB.
     * 
     */
    public void setIntB(int value) {
        this.intB = value;
    }

}
