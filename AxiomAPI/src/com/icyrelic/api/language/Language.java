package com.icyrelic.api.language;

import com.icyrelic.api.util.Format;

/**
 * @author IcyRelic
 */
public enum Language {

    API_PREFIX                      ("AxiomAPI"         , "API_PREFIX"                  , "[AxiomAPI]"                                                                    ),
    INSUFFICIENT_ACCESS             ("AxiomAPI"         , "INSUFFICIENT_ACCESS"         , "Error: Insufficient Access."                                                   ),
    PLAYER_NOT_FOUND                ("AxiomAPI"         , "PLAYER_NOT_FOUND"            , "Error: Player not found."                                                      ),
    COMMAND_NOT_FOUND               ("AxiomAPI"         , "COMMAND_NOT_FOUND"           , "Error: Command Not Found."                                                     ),
    INVALID_INTEGER                 ("AxiomAPI"         , "INVALID_INTEGER"             , "Error: Invalid Number."                                                        ),
    COMMANDS_MAPPED                 ("AxiomAPI"         , "COMMANDS_MAPPED"             , "All commands have been mapped"                                                 ),
    EVENTS_MAPPED                   ("AxiomAPI"         , "EVENTS_MAPPED"               , "All events have been mapped"                                                   ),
    INVALID_USAGE_APIMAP            ("AxiomAPI"         , "INVALID_USAGE_APIMAP"        , "Error: Invalid Usage ( /<command> <comands | events> )"                        ),
    MUST_BE_PLAYER                  ("AxiomAPI"         , "MUST_BE_PLAYER"              , "Error: You must be a player to do that"                                        ),

    CHAT_PREFIX                     ("AxiomChat"        , "CHAT_PREFIX"                 , "[AxiomChat]"                                                                   ),
    INVALID_USAGE_MUTE              ("AxiomChat"        , "INVALID_USAGE_MUTE"          , "Error: Invalid Usage ( /<command> <player> <reason> )"                         ),
    INVALID_USAGE_UNMUTE            ("AxiomChat"        , "INVALID_USAGE_UNMUTE"        , "Error: Invalid Usage ( /<command> <player> )"                                  ),
    PLAYER_MUTED                    ("AxiomChat"        , "PLAYER_MUTED"                , "You are muted."                                                                ),
    MUTE_SENDER                     ("AxiomChat"        , "MUTE_SENDER"                 , "You have muted %player% for %reason%"                                          ),
    MUTE_TARGET                     ("AxiomChat"        , "MUTE_TARGET"                 , "You have been muted by %player% for %reason%"                                  ),
    MUTE_GLOBAL                     ("AxiomChat"        , "MUTE_GLOBAL"                 , "%target% has been muted by %player% for %reason%"                              ),
    UNMUTE_SENDER                   ("AxiomChat"        , "UNMUTE_SENDER"               , "You have unmuted %player%"                                                     ),
    UNMUTE_TARGET                   ("AxiomChat"        , "UNMUTE_TARGET"               , "You have been unmuted by %player%"                                             ),
    UNMUTE_GLOBAL                   ("AxiomChat"        , "UNMUTE_GLOBAL"               , "%target% has been unmuted by %player%"                                         ),
    CHAT_FAILED                     ("AxiomChat"        , "CHAT_FAILED"                 , "You are unable to chat"                                                        ),

    MOBCASH_PREFIX                  ("AxiomMobCash"     , "MOBCASH_PREFIX"              , "[AxiomMobCash]"                                                                ),
    MONEY_EARNED                    ("AxiomMobCash"     , "MONEY_EARNED"                , "You have earned $%money% from killing a %mob%."                                  ),

    /*SHOP_PREFIX                     ("AxiomShop"        , "SHOP_PREFIX"                 , "[AxiomShop]"                                                                 ),
    INVALID_SHOP                    ("AxiomShop"        , "INVALID_SHOP"                , "Error: Invalid Shop"                                                           ),
    CHEST_NOT_FOUND                 ("AxiomShop"        , "CHEST_NOT_FOUND"             , "Error: Chest Not Found"                                                        ),
    SELLER_CHEST_NOT_FOUND          ("AxiomShop"        , "SELLER_CHEST_NOT_FOUND"      , "Error: Your shop is missing a chest"                                           ),
    OUT_OF_STOCK                    ("AxiomShop"        , "OUT_OF_STOCK"                , "Notice: This shop is out of stock"                                             ),
    CHEST_FULL                      ("AxiomShop"        , "CHEST_FULL"                  , "Notice: The sellers chest is full"                                             ),
    SELLER_CHEST_FULL               ("AxiomShop"        , "SELLER_CHEST_FULL"           , "Notice: Your shops chest is full"                                              ),
    NOT_ENOUGH_TO_SELL              ("AxiomShop"        , "NOT_ENOUGH_TO_SELL"          , "Error: You dont have enough of this item"                                      ),
    SELLER_CANT_BUY                 ("AxiomShop"        , "SELLER_CANT_BUY"             , "Notice: The seller doesnt have enough money"                                   ),
    SELLER_OUT_OF_STOCK             ("AxiomShop"        , "SELLER_OUT_OF_STOCK"         , "Notice: Your shop is out of stock"                                             ),
    INVALID_SHOP_MATERIAL           ("AxiomShop"        , "INVALID_SHOP_MATERIAL"       , "Error: Invalid Material"                                                       ),
    INVALID_SHOP_DATA               ("AxiomShop"        , "INVALID_SHOP_DATA"           , "Error: Invalid Data"                                                           ),
    INVALID_SHOP_PRICE              ("AxiomShop"        , "INVALID_SHOP_PRICE"          , "Error: Invalid Price"                                                          ),
    INVALID_SHOP_AMOUNT             ("AxiomShop"        , "INVALID_SHOP_AMOUNT"         , "Error: Invalid Amount"                                                         ),
    SHOP_CREATED                    ("AxiomShop"        , "SHOP_CREATED"                , "Shop Created"                                                                  ),
    SHOP_DELETED                    ("AxiomShop"        , "SHOP_DELETED"                , "Shop Deleted"                                                                  ),
    SHOP_DELETETION_FAILED          ("AxiomShop"        , "SHOP_DELETETION_FAILED"      , "You cant delete this shop."                                                    ),
    ITEM_BOUGHT                     ("AxiomShop"        , "ITEM_BOUGHT"                 , "You have bought %amount% %item% for $%price%"                                  ),
    ITEM_SOLD                       ("AxiomShop"        , "ITEM_SOLD"                   , "You have sold %amount% %item% for $%price%"                                    ),
    SELLER_SOLD                     ("AxiomShop"        , "SELLER_SOLD"                 , "You have sold %amount% %item% for $%price% to %player%"                        ),
    SELLER_BOUGHT                   ("AxiomShop"        , "SELLER_BOUGHT"               , "%player% has sold you %amount% %item% for $%price%"                            ),
*/
    LIST_PREFIX                     ("AxiomList"        , "LIST_PREFIX"                 , "[AxiomList]"                                                                   ),
    LIST_HEAD                       ("AxiomList"        , "LIST_HEAD"                   , "----------[ Online Users %online%/%max% ]----------"                           ),
    LIST_FORMAT                     ("AxiomList"        , "LIST_FORMAT"                 , "%prefix%&r: %users%"                                                      ),
    INVALID_USAGE_LIST              ("AxiomList"        , "INVALID_USAGE_LIST"          , "Error: Invalid Usage ( /<command> )"                                           ),

    SPAWN_PREFIX                    ("AxiomSpawn"       , "SPAWN_PREFIX"                , "[AxiomSpawn]"                                                                  ),
    INVALID_USAGE_SET_SPAWN         ("AxiomSpawn"       , "INVALID_USAGE_SET_SPAWN"     , "Error: Invalid Usage ( /<command> )"                                           ),
    INVALID_USAGE_SPAWN             ("AxiomSpawn"       , "INVALID_USAGE_SPAWN"         , "Error: Invalid Usage ( /<command> )"                                           ),
    SPAWN_SET                       ("AxiomSpawn"       , "SPAWN_SET"                   , "Spawnpoint has been Set"                                                       ),
    TELEPORTED_TO_SPAWN             ("AxiomSpawn"       , "TELEPORTED_TO_SPAWN"         , "Teleported to spawn."                                                          ),
    NO_SPAWN                        ("AxiomSpawn"       , "NO_SPAWN"                    , "Error: Spawnpoint not set"                                                     ),

    HOME_PREFIX                     ("AxiomHome"        , "HOME_PREFIX"                 , "[AxiomHome]"                                                                   ),
    TELEPORTED_HOME                 ("AxiomHome"        , "TELEPORTED_HOME"             , "Teleported Home"                                                               ),
    HOME_LIST                       ("AxiomHome"        , "HOME_LIST"                   , "Homes: %homes%"                                                                ),
    HOME_SET                        ("AxiomHome"        , "HOME_SET"                    , "Home '%name%' set"                                                             ),
    NO_HOMES                        ("AxiomHome"        , "NO_HOMES"                    , "Error: You dont have any homes"                                                ),
    MAX_HOMES                       ("AxiomHome"        , "MAX_HOMES"                   , "Error: You have the maxium amount of homes"                                    ),
    HOME_NOT_FOUND                  ("AxiomHome"        , "HOME_NOT_FOUND"              , "Error: Home not found"                                                         ),
    INVALID_USAGE_SET_HOME          ("AxiomHome"        , "INVALID_USAGE_SET_HOME"      , "Error: Invalid Usage ( /<command> [<name>]"                                    ),
    INVALID_USAGE_HOME_LIST         ("AxiomHome"        , "INVALID_USAGE_HOME_LIST"     , "Error: Invalid Usage ( /<command>"                                             ),
    INVALID_USAGE_HOME              ("AxiomHome"        , "INVALID_USAGE_HOME"          , "Error: Invalid Usage ( /<command> [<name>]"                                    ),

    ADMIN_PREFIX                    ("AxiomAdmin"       , "ADMIN_PREFIX"                , "[AxiomAdmin]"                                                                  ),
    INVALID_USAGE_BAN               ("AxiomAdmin"       , "INVALID_USAGE_BAN"           , "Error: Invalid Usage ( /<command> <player> <reason>"                           ),
    INVALID_USAGE_TEMP_BAN          ("AxiomAdmin"       , "INVALID_USAGE_TEMP_BAN"      , "Error: Invalid Usage ( /<command> <player> <time> <min|hour|day> <reason>"     ),
    INVALID_USAGE_PERM_BAN          ("AxiomAdmin"       , "INVALID_USAGE_PERM_BAN"      , "Error: Invalid Usage ( /<command> <player> <reason>"                           ),
    INVALID_USAGE_KICK              ("AxiomAdmin"       , "INVALID_USAGE_KICK"          , "Error: Invalid Usage ( /<command> <player> <reason>"                           ),
    INVALID_USAGE_WARN              ("AxiomAdmin"       , "INVALID_USAGE_WARN"          , "Error: Invalid Usage ( /<command> <player> <reason>"                           ),
    INVALID_USAGE_UNBAN             ("AxiomAdmin"       , "INVALID_USAGE_UNBAN"         , "Error: Invalid Usage ( /<command> <player>"                                    ),
    INVALID_USAGE_CHECK_BAN         ("AxiomAdmin"       , "INVALID_USAGE_CHECK_BAN"     , "Error: Invalid Usage ( /<command> <player>"                                    ),
    INVALID_TIME                    ("AxiomAdmin"       , "INVALID_TIME"                , "Error: Invalid time. Valid times: minute, hour, day"                           ),
    ERROR_USER_BANNED               ("AxiomAdmin"       , "ERROR_USER_BANNED"           , "Error: Player already banned."                                                 ),
    ERROR_UNBAN                     ("AxiomAdmin"       , "ERROR_UNBAN"                 , "Error: Player is permanently banned"                                           ),
    ERROR_USER_NOT_BANNED           ("AxiomAdmin"       , "ERROR_USER_NOT_BANNED"       , "Error: Player not banned."                                                     ),
    BANNED_PLAYER                   ("AxiomAdmin"       , "BANNED_PLAYER"               , "%player% has been banned by %admin%. Reason: %reason%"                         ),
    PERM_BANNED_PLAYER              ("AxiomAdmin"       , "PERM_BANNED_PLAYER"          , "%player% has been perm banned by %admin%. Reason: %reason%"                    ),
    TEMP_BANNED_PLAYER              ("AxiomAdmin"       , "TEMP_BANNED_PLAYER"          , "%player% has been temp banned by %admin% until %unban%. Reason: %reason%"      ),
    UNBAN_PLAYER                    ("AxiomAdmin"       , "UNBAN_PLAYER"                , "%player% has been unbanned by %admin%"                                         ),
    KICKED_PLAYER                   ("AxiomAdmin"       , "KICKED_PLAYER"               , "%player% has been kicked by %admin%. Reason: %reason%"                         ),
    BANNED_JOIN                     ("AxiomAdmin"       , "BANNED_JOIN"                 , "Banned\nBanned By: %admin%\nReason %reason%"                                   ),
    PERM_BANNED_JOIN                ("AxiomAdmin"       , "PERM_BANNED_JOIN"            , "Permanently Banned.\nBanned By: %admin%\nReason %reason%"                      ),
    TEMP_BANNED_JOIN                ("AxiomAdmin"       , "TEMP_BANNED_JOIN"            , "Temporarily Banned.\nBanned By: %admin%\nReason %reason%\nUntil: %unban%"      ),
    KICKED                          ("AxiomAdmin"       , "KICKED"                      , "Kicked.\nBanned By: %admin%\nReason %reason%"                                  ),
    WARNING                         ("AxiomAdmin"       , "WARNING"                     , "%player% has recieved a warning from %admin%\n > %reason%"                     ),
    BAN_INFO                        ("AxiomAdmin"       , "BAN_INFO"                    , "Player: %player%\nBanned: %banned%"                                            ),
    RECENT_BAN                      ("AxiomAdmin"       , "RECENT_BAN"                  , "Recent Ban:\n - Type: %type%\n - Admin: %admin%\n - Until: %until%\n - Reason: %reason%"),
    BAN_TOTALS                      ("AxiomAdmin"       , "BAN_TOTALS"                  , "\nNumber of:\n - Bans: %bans%\n - Kicks: %kicks%\n - Warnings: %warnings%\n - Reports: %reports%"),
    NO_RECENT_BAN                   ("AxiomAdmin"       , "NO_RECENT_BAN"               , "\nPlayer has never been banned"                                                ),
/*
    LOCK_PREFIX                     ("AxiomLock"        , "LOCK_PREFIX"                 , "[AxiomAdmin]"                                                                  ),
    LOCK_BROKE                      ("AxiomLock"        , "LOCK_BROKE"                  , "Lock has been removed."                                                        ),
    CANT_BREAK_LOCK                 ("AxiomLock"        , "CANT_BREAK_LOCK"             , "You cannot break that lock"                                                    ),
    LOCK_PLACED                     ("AxiomLock"        , "LOCK_PLACED"                 , "Lock created"                                                                  ),
    USER_ADDED                      ("AxiomLock"        , "USER_ADDED"                  , "%player% has been added to the lock"                                           ),
    USER_REMOVED                    ("AxiomLock"        , "USER_REMOVED"                , "$player% has been removed from the lock"                                       ),
    LOCKS_CLEARED                   ("AxiomLock"        , "LOCKS_CLEARED"               , "%player%'s locks have been cleared"                                            ),
    LOCKED                          ("AxiomLock"        , "LOCKED"                      , "Sorry, That is locked."                                                        ),
*/
    MESSAGES_PREFIX                 ("AxiomMessages"    , "MESSAGES_PREFIX"             , "[AxiomMessages]"                                                               ),
    AUTO_MESSAGE_PREFIX             ("AxiomMessages"    , "AUTO_MESSAGE_PREFIX"         , "[AutoMessage]"                                                                 ),
    INVALID_USAGE_SAY               ("AxiomMessages"    , "INVALID_USAGE_SAY"           , "Error: Invalid Usage ( /<command> <message>"                                   ),
    INVALID_USAGE_FAKE              ("AxiomMessages"    , "INVALID_USAGE_FAKE"          , "Error: Invalid Usage ( /<command> join|quit"                                   ),
    INVALID_USAGE_AMRELOAD          ("AxiomMessages"    , "INVALID_USAGE_AMRELOAD"      , "Error: Invalid Usage ( /<command>"                                             ),
    SAY_PREFIX                      ("AxiomMessages"    , "SAY_PREFIX"                  , "[Say]"                                                                         ),
    MESSAGE_JOIN                    ("AxiomMessages"    , "MESSAGE_JOIN"                , "%player% has joined."                                                          ),
    MESSAGE_FIRST_JOIN              ("AxiomMessages"    , "MESSAGE_FIRST_JOIN"          , "Welcome %player% to the server!"                                               ),
    MESSAGE_QUIT                    ("AxiomMessages"    , "MESSAGE_QUIT"                , "%player% has left."                                                            ),
    MESSAGE_RELOAD                  ("AxiomMessages"    , "MESSAGE_RELOAD"              , "Config Reloaded"                                                               ),
    MESSAGE_OF_THE_DAY              ("AxiomMessages"    , "MESSAGE_OF_THE_DAY"          , "Hello %player% Welcome to our server.\nRemember to always have fun."           ),
    SILENT_JOIN                     ("AxiomMessages"    , "SILENT_JOIN"                 , "You have silently joined the server."                                          ),
    
    DEATHMSG_UNKNOWN                ("AxiomMessages"    , "DEATHMSG_UNKNOWN"            , "(UNKNOWN) %player% was killed by %killer% with %weapon%"                       ),
    DEATHMSG_PLAYER                 ("AxiomMessages"    , "DEATHMSG_PLAYER"             , "%player% was killed by %killer% with %weapon%"                                 ),

    DEATHMSG_BAT                    ("AxiomMessages"    , "DEATHMSG_BAT"                , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_CHICKEN                ("AxiomMessages"    , "DEATHMSG_CHICKEN"            , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_COW                    ("AxiomMessages"    , "DEATHMSG_COW"                , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_MOOSHROOM              ("AxiomMessages"    , "DEATHMSG_MOOSHROOM"          , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_PIG                    ("AxiomMessages"    , "DEATHMSG_PIG"                , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_RABBIT                 ("AxiomMessages"    , "DEATHMSG_RABBIT"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SHEEP                  ("AxiomMessages"    , "DEATHMSG_SHEEP"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SKELETON_HORSE         ("AxiomMessages"    , "DEATHMSG_SKELETON_HORSE"     , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SQUID                  ("AxiomMessages"    , "DEATHMSG_SQUID"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_VILLAGER               ("AxiomMessages"    , "DEATHMSG_VILLAGER"           , "%player% was killed by %killer% with %weapon%"                                 ),

    DEATHMSG_CAVE_SPIDER            ("AxiomMessages"    , "DEATHMSG_CAVE_SPIDER"        , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ENDERMAN               ("AxiomMessages"    , "DEATHMSG_ENDERMAN"           , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_POLAR_BEAR             ("AxiomMessages"    , "DEATHMSG_POLAR_BEAR"         , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SPIDER                 ("AxiomMessages"    , "DEATHMSG_SPIDER"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_PIGMAN                 ("AxiomMessages"    , "DEATHMSG_PIGMAN"             , "%player% was killed by %killer% with %weapon%"                                 ),
    
    DEATHMSG_BLAZE                  ("AxiomMessages"    , "DEATHMSG_BLAZE"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_CREEPER                ("AxiomMessages"    , "DEATHMSG_CREEPER"            , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ELDER_GUARDIAN         ("AxiomMessages"    , "DEATHMSG_ELDER_GUARDIAN"     , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ENDERMITE              ("AxiomMessages"    , "DEATHMSG_ENDERMITE"          , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_EVOKER                 ("AxiomMessages"    , "DEATHMSG_EVOKER"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_GHAST                  ("AxiomMessages"    , "DEATHMSG_GHAST"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_GUARDIAN               ("AxiomMessages"    , "DEATHMSG_GUARDIAN"           , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_HUSK                   ("AxiomMessages"    , "DEATHMSG_HUSK"               , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_MAGMA_CUBE             ("AxiomMessages"    , "DEATHMSG_MAGMA_CUBE"         , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SHULKER                ("AxiomMessages"    , "DEATHMSG_SHULKER"            , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SILVERFISH             ("AxiomMessages"    , "DEATHMSG_SILVERFISH"         , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SKELETON               ("AxiomMessages"    , "DEATHMSG_SKELETON"           , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SLIME                  ("AxiomMessages"    , "DEATHMSG_SLIME"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_STRAY                  ("AxiomMessages"    , "DEATHMSG_STRAY"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_VEX                    ("AxiomMessages"    , "DEATHMSG_VEX"                , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_VINDICATOR             ("AxiomMessages"    , "DEATHMSG_VINDICATOR"         , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_WITCH                  ("AxiomMessages"    , "DEATHMSG_WITCH"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_WITHER_SKELETON        ("AxiomMessages"    , "DEATHMSG_WITHER_SKELETON"    , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ZOMBIE                 ("AxiomMessages"    , "DEATHMSG_ZOMBIE"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ZOMBIE_VILLAGER        ("AxiomMessages"    , "DEATHMSG_ZOMBIE_VILLAGER"    , "%player% was killed by %killer% with %weapon%"                                 ),

    DEATHMSG_DONKEY                 ("AxiomMessages"    , "DEATHMSG_DONKEY"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_HORSE                  ("AxiomMessages"    , "DEATHMSG_HORSE"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_LLAMA                  ("AxiomMessages"    , "DEATHMSG_LLAMA"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_MULE                   ("AxiomMessages"    , "DEATHMSG_MULE"               , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_OCELOT                 ("AxiomMessages"    , "DEATHMSG_OCELOT"             , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_WOLF                   ("AxiomMessages"    , "DEATHMSG_WOLF"               , "%player% was killed by %killer% with %weapon%"                                 ),
    
    DEATHMSG_IRON_GOLEM             ("AxiomMessages"    , "DEATHMSG_IRON_GOLEM"         , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_SNOW_GOLEM             ("AxiomMessages"    , "DEATHMSG_SNOW_GOLEM"         , "%player% was killed by %killer% with %weapon%"                                 ),
    
    DEATHMSG_ENDER_DRAGON           ("AxiomMessages"    , "DEATHMSG_ENDER_DRAGON"       , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_WITHER                 ("AxiomMessages"    , "DEATHMSG_WITHER"             , "%player% was killed by %killer% with %weapon%"                                 ),
    
    DEATHMSG_GIANT                  ("AxiomMessages"    , "DEATHMSG_GIANT"              , "%player% was killed by %killer% with %weapon%"                                 ),
    DEATHMSG_ZOMBIE_HORSE           ("AxiomMessages"    , "DEATHMSG_ZOMBIE_HORSE"       , "%player% was killed by %killer% with %weapon%"                                 ),

    DEATHMSG_DROWNING               ("AxiomMessages"    , "DEATHMSG_DROWNING"           , "%player% drowned"                                                              ),
    DEATHMSG_FALLING                ("AxiomMessages"    , "DEATHMSG_FALLING"            , "%player% took a leap of faith"                                                 ),
    DEATHMSG_FIRE                   ("AxiomMessages"    , "DEATHMSG_FIRE"               , "%player% went up in flames"                                                    ),
    DEATHMSG_SUFFOCATION            ("AxiomMessages"    , "DEATHMSG_SUFFOCATION"        , "%player% suffocated to death"                                                  ),
    DEATHMSG_VOID                   ("AxiomMessages"    , "DEATHMSG_VOID"               , "%player% fell into the void"                                                   ),
    DEATHMSG_STARVATION             ("AxiomMessages"    , "DEATHMSG_STARVATION"         , "%player% experienced a famine"                                                 ),
    DEATHMSG_SUICIDE                ("AxiomMessages"    , "DEATHMSG_SUICIDE"            , "%player% and thats all she said"                                               ),
    DEATHMSG_MAGIC                  ("AxiomMessages"    , "DEATHMSG_MAGIC"              , "%player% died at the hands of magic"                                           ),
    DEATHMSG_POISON                 ("AxiomMessages"    , "DEATHMSG_POISON"             , "%player% was poisened"                                                         ),
    DEATHMSG_LIGHTENING             ("AxiomMessages"    , "DEATHMSG_LIGHTENING"         , "%player% was struck by lightening and died instantly"                          ),
    DEATHMSG_LAVA                   ("AxiomMessages"    , "DEATHMSG_LAVA"               , "%player% fell into lava"                                                       ),
    DEATHMSG_FIRE_TICK              ("AxiomMessages"    , "DEATHMSG_FIRE_TICK"          , "%player% burned to death"                                                      ),
    DEATHMSG_CONTACT                ("AxiomMessages"    , "DEATHMSG_CONTACT"            , "%player% ran into a cactus"                                                    ),
    DEATHMSG_BLOCK_EXPLOSION        ("AxiomMessages"    , "DEATHMSG_BLOCK_EXPLOSION"    , "%player% blew up."                                                             ),
    DEATHMSG_FIREWORK               ("AxiomMessages"    , "DEATHMSG_FIREWORK"           , "%player% took a firework to the face"                                          ),




    ;


    String pluginName;
    String name;
    String message;

    Language(String pluginName, String name, String message){
        this.pluginName = pluginName;
        this.name = name;
        this.message = message;
    }

    public String getPluginName(){
        return pluginName;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return Format.colorString(message);
    }

    public String getMessage(Language prefix) {
        return Format.colorString(prefix.getMessage() + " " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return getMessage();
    }


}
