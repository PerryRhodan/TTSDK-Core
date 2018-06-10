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
		public int calcSomething() {
			return some_other_value + some_value;
		}
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
