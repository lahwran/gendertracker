gender tracker protocol
=======================


meta
----

Uses capsystem and commsystem. capsystem capability is "+gendermod:v0" and commsystem ID is "Irwp".


commsystem
----------

the commsystem messages are blah


initialization
--------------

after capsystem negotiation is complete, client sends current gender
in a commsystem message. this message is a single boolean, where positive is female.


requesting other players
------------------------

when the client needs to render a player that it does not have recorded, 
it sends a single string of the player whos gender is needed. 
the server responds with a string followed by a boolean, where the string is 
the player who's gender is being notified. The client then records this for fast access.