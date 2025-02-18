
package userlogin;
import javafx.beans.property.SimpleStringProperty; // used to create and manage JavaFX properties.
import javafx.beans.property.StringProperty;
public abstract class Result {
     private final StringProperty result; // will hold a string value and is used to support JavaFX's property binding.

    public Result(String result) {     //The constructor initializes the result property with the provided string value by creating a new object of SimpleStringProperty.
        this.result = new SimpleStringProperty(result);
    }

    public String getResult() { 
        return result.get();
    }

    public void setResult(String value) { //Allows updating the value of the result property.
        result.set(value);
    }

    public StringProperty resultProperty() {  // This is particularly useful for dynamic updates in a GUI, 
                                               //such as automatically updating a displayed value when the underlying data changes.
        return result;
    }
}
