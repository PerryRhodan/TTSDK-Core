# TTSDK-Core

#### Abstraction - IDed
  Assigns a unique ID to the instance of an extending class.
  This is achieved by keeping count of the current highest id 
  across all extending class instances.

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
