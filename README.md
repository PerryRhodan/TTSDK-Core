# TTSDK-Core

#### Abstraction - IDed
  Assigns a unique ID to the instance of an extending class.
  This is achieved by keeping count of the current highest id 
  across all extending class instances.
  
  For example the following class extends IDed:
```java
class Testclass extends IDed {
    // The class does something with
    // some member vars
    int some_value;
    int some_other_value;
    
    public Testclass(int value) {
    	// nothing to be done here in terms of IDs.
	some_value = value;
	some_other_value = value * value;
    }
    // methods, etc ...
}
```
Now new instances can be created without having to worry about assigning a unique ID:
```java
Testclass instance_a = new Testclass(5);
Testclass instance_b = new Testclass(18);
System.out.println("instance_a.getId(): " + instance_a.getId());  // prints 0
System.out.println("instance_b.getId(): " + instance_b.getId());  // prints 1
```

#### Datastring
  Class to convert data into a string and back.
  Meant to support write data to file in a human 
  readable manner, in case you don't want to use
  something like json.
  
  Reusing the Testclass from above, the can be used like this:
```java
// Set up with a unique delimiter that 
// wont be used in your data itself
DataString ds = new DataString("_TDL_");
		
// Get the data string
ds.Add("some_value", "" + instance_a.some_value);
ds.Add("some_other_value", "" + instance_a.some_other_value);
		
String datastring = ds.GetDataString();
System.out.println(datastring); // prints some_value:5_TDL_some_other_value:25
```

This datastring could then be written to a file or transfered somewhere. When reading the string to actual data again, be careful to check for types correctly, as this not yet checked by the current implementation.

```java
// Get data from the string
ds.ReadDataString(datastring);
instance_a.some_value = Integer.parseInt(ds.Read("some_value"));
instance_a.some_other_value = Integer.parseInt(ds.Read("some_other_value"));
ds.Clear(); // clear before you use this instance again
```

This also works for items of a list. For instance assuming the Testclass contained a list named "value_list":

```java
for (Integer value_in_list : instance_a.value_list) {
    ds.Add("specific_list_item", "" + value_in_list);
}
datastring = ds.GetDataString();

// which can then be read via
ds.ReadDataString(datastring);
for (String datavalue : ds.ReadAll("specific_list_item")) {
    instance_a.value_list.add(Integer.parseInt(datavalue));
}
```

  
#### Log
  Allows to output different levels of 
  logging information. Only the desired 
  level will be printed.
  
#### Thread
  Thread (which might be a poor choice of name) 
  offers an convenient way to execute certain thread tasks
  periodically.
  Thread tasks execute one after each other, so
  they are not precise in their timings, and a blocking task
  will block all subsequent tasks!
  
#### Value - AngleValue
  Holds angle values from [0, 360] degrees, where 0 deg is
  the same as 360 deg. Offers some functionality to establish
  the difference between two angles.
