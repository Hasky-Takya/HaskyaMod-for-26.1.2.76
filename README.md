
Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/

Links: 
==========
https://github.com/Hasky-Takya/HaskyaMod

初代版本简述： 
==========
我们添加了一些物品：青辉石、草莓、草莓牛奶
我们添加了一个矿石：青辉石矿
我们添加了一些实体：桃井和绿
我们可以在0至-64格的范围内寻找到自然生成的青辉石矿石，用铁镐挖掘能够获得一个青辉石
青辉石可食用，会获得幸运buff
桃井和绿会在世界中生成，在雪地相关群系，她们可能会穿上冬装
桃井和绿喜欢青辉石，如果地上有掉落的青辉石，她们会冲过去捡起来
桃井和绿可被驯化，她们会上交自己所有的青辉石给她们的主人
桃井和绿会受伤，可以用草莓牛奶治愈
草莓牛奶制作方式：用8颗草莓围绕牛奶桶
你会在村庄箱子里找到草莓，破坏掉草也有概率获得草莓
如果桃井或绿被击杀后，也会掉落一颗草莓

【以上为初代版本简述，具体后续修改信息以各release的描述为准】
