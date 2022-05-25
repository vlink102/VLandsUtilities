```
Functionality and Utility plugin for VLands Network (Kit-PvP)
Authors: V_Link
Contributors: minion325 (Major thanks)
Library credits: BoostedYAML (dejvokep), RedLib (Redempt), EffectLib (~NathanWolf).

Key:
  (c-<?>): Configurable value, when used after a word, refers to the word. When used alone, refers to the next or previous word in context
       E.g. (c) minutes, where (c) is the time in minutes.   ~ (value from config) minutes.            ~ 3 minutes
       E.g. potioneffect(c), where (c) is the potioneffect.  ~ potioneffect(enchantment from config).  ~ ABSORPTION
  (?): May already exist as a standalone spigot project
  (v?): Version compatibility question
  (p?): Is this even possible to do efficiently/easily
  (c?): Possible conflict with another plugin, but can be fixed

Todo Layout:
  '-': New Item in list.
  '>': Section - Put related/similar things in this.

  If an item/section is indented from an item/section in the list, it corresponds to that item.
  If an item/section is on the same indentation level, the item/section corresponds to the previous key

  E.g.
  To-do:
    - Re-paint the apartment walls
      - Use coconut white
    > Learn to fly
      - Learn to not write overly complicated to-do lists first
      > Flying basics
        - Gather a lot of feathers
        - Get a life

To-do:
  - Configuration Files
  - Disable Features function
  - Debug Mode - GenericUtils.debug()
  > Ideas:
    - Projectile Trails
    - Player Trails (?)
  > Custom Items:
    - Throwable Fireball
    - Throwable TNT
    - GoldenHead
    - SpawnPack
    > Ideas:
      - Throwable Lava/Water (No extra info needed) (c?:BlockRestrictor non-player-placed blocks)
      - Panic Item (Brick Box, heals player(c-Boolean), dissapears after (c-Double) time.)
      - Increase Enchantment Level Item (Random enchantment(c-Enchantment) per item, Random enchantment increase amount(c-Integer:level upgrade))
      - Russian Roulette (Certain chance of being killed, random per item, (c-String:Split to parseint) chance range, has no other purpose just for fun)
      - Deathly Dice (Roll the dice on the floor, Gives a random(c-Item) OP item, (c-Double) time to kill a player or die) (p?:Custom roll animation)
        > Commands:
          - AddDiceItem <name> (Item in hand, param1 is internal name for commands)
          - RemoveDiceItem <name> (Internal name of Dice item)
      - Implosion (Applies velocity of (c-Integer) entities nearby, towards player)
      - Vortex (Sucks (c-Integer) entities/players nearby(c-Double) towards the other player for (c-Double) time, dealing (c-Double) damage) (p?:smoothly move entity)
      - Ground slam (Prevents (c-Integer) players/entites nearby(c-Double) from jumping for (c-Double) time, applying effect(c-PotionEffect)) (p?:prevent jump)
      - Energy Blast (Launches (c-Integer) entities/players nearby(c-Double) away from the player, dealing (c-Double) damage)
      - Silent Killer (Grants invisiblity for (c-Integer) seconds and teleports behind closest player.)
      - Launch (Launches player forward in direction they're facing.)
      - Laser (Guardian beam from player's eyes, damaging players for (c-Double) time.) (p?)
      - ShockWave (Effects (c-Integer) players/entities nearby(c-Double) with enchantments(c-Enchantment:blindness,slowness,nausea) (p?:Custom shockwave effect, explosion at player, then using circle of white particles expanding outwards as the shockwave.)
      - Mine (Invisible(p?:Armor-stands or silverfish/endermite with invisibility) bomb, deals (c-Double) damage and launches nearby(c-Double) players/entities away from the explosion. Maximum of (c-Integer) mines placed allowed.) (p?:make only visible to the placer, and can be disarmed by the placer by right-clicking the mine.)
      - Heat-seeking missile (Self explanatory, sends a firework/fireball/particle line trail(p?) towards a player, will hit target indefinitely. Deals 0 damage to non-targetted players.) (p?:Lock target system, maybe use ray-cast from player eye direction (only activate when looking at player), send message 'Target Locked: PlayerName')
      - LifeSteal (Gives (c-Integer) hearts to the player and takes (c-Integer) from the opponent.)
      - Curse Ability (Makes the opponent only able to damage themselves for (c-Double) time. Only works with melee and ranged, will deal (c-Integer) hearts every time they hit, no matter the item.)
      - GraveYard ability (Spawns skeletons in a radius(c-Double), for (c-Double) time. Skeletons have no bow but still deal damage) (p?:idk if skeletons do damage if they dont have a bow, never tested)
  > Trivial Commands:
    - GetUUID
    - SetSkull
    - Announce
    - DemoTroll
    - Masssay
    - F-Command
