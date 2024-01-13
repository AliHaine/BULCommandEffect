# BulPotionEffect

This Minecraft plugin allows the creation of custom commands linked to specified potion effects, with a cooldown and permission system. Compatible with version 1.8 to the Latest Minecrat version. Link to download UNKNOW

## Features

- Create custom commands and link him to specified potion effects
- Admin command to give and remove effect
- Apply potion effects to a player upon their first join on the server
- Define a permission for the use of the custon commands you have created
- Set cooldown between each use to the custom commands
- Set the duration of the effects applied
- Fully configurable

## Configuration file

> Define the effects that will be given to the player upon their first join

give_by_default: [nightvision_example, example1]

> Define your own effects here without any limit

#####
    effects:
       example1:                                        <--- The name of your effect, have to be unique
          commands: [/mysuper command]                  <--- List of the commands who active your effect
          effects: [REGENERATION 1, INCREASE_DAMAGE 2]  <--- List of the Potion effects and their amplifier
          duration: 60                                  <--- Duration of the Potion effects (0 for infinite)
          cooldown: 300                                 <--- Cooldown between each use of your effect
          perm: my.custom.permission                    <--- Permission to allow the use of your effect
        nightvision_example:
           commands: [/nv, /nightvision, /night vision]
           effects: [NIGHT_VISION 1]
           duration: 99999
           cooldown: 10
           perm: nightvision.permission
    
> Define all the messages and actionbar text. If you don't want a specifique message you can define nothing beetwen the two "" quotation

messages:   
  effect_activate: "&aThe effect %effect% has been activated"   
  effect_disable: "&cThe effect %effect% has been disable"     
  effect_give: "&aYou have give the effect %effect% to the target player"   
  effect_remove: "&aYou have remove the effect %effect% to the target player"   
  effect_on_cooldown: "&cYou have to wait %time%   before using again this effect"   
  error_player_exist: "&cThe target player are not online or don't exist"   
  error_effect_exist: "&cThis effect don't exist"   
  error_config_effect_exist: "&cERROR: the potion effect &e%potion% &cdon't exist"   
  error_effect_permission: "&cYou don't have the permssion to use the effect %effect%"   
  error_unknown_command: "&cThis command don't exist"   
  config_reload: "&aConfiguration reloaded"   
  no_permission: "&cYou don't have the permission to do that"   

## Commands and permissions

| Command | Description | Permission |
| ------ | ------ | ------|
| bulpotioneffect give <player> <effect> | Give the effect to the player | bulpotioneffect.admin
| bulpotioneffect remove <player> <effect> | Remove the effect to the player | bulpotioneffect.admin
| bulpotioneffect reload | Reload the configuration file | bulpotioneffect.admin
|  | Bypass the cooldown for every effect | bulpotioneffect.bypass.cooldown

## Distribution

This is a public plugin. You are free to use it and create a fork to develop your own version. However you are not allowed to sell or distribute it in a private manner.