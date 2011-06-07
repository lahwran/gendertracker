SMP Gender Tracker
==================

for iPixeli's gender mod, and as a test of capsystem's viability


use
---

- import net.mcclient.gendertracker.GenderTracker;
- somewhere that will run on startup, you must GenderTracker.setLocalGender(getter) where getter is an instance of your implementation of GenderTracker.LocalGender. Your implementation should return true from isFemale() when the local player has their settings set that way.
- To get another player's gender, call GenderTracker.isPlayerFemale(otherPlayerName). While GenderTracker.shouldCache(otherPlayerName) is false, you must call isPlayerFemale any time you want to find out. once shouldCache is true, however, it indicates that the server has responded as to the player's gender status and it may safely be put into a field on the player object.
