# Hammer_Euv - a Terminal MVVM UI Engine

*if you have a hammer, everything looks like a nail*

Hammer_Euv is a terminal UI engine based on my CS 611 OOD homework 4. The basic idea is I prefer to deal with my assignment tasks by using front-end way/patterns/styles.

## Artchiture
### Intefaces
#### ModelProperty

This interface defines essential methods for two-way binding properties in model. The ViewModel could use this interface and Reflection(will be introduced later) to interating all ModelProperty in your Model.

#### View

Defines essential methods for a View.

#### ViewModel

Defines a viewmodel control and logic interface.

#### Model

All model must implements this interface. Even it's a empty interface, It may add some members in the following version

#### UIEngine

UI Engine handles all stuffs relate to ui.

### Classes

#### GameEngine

![](./readme/engine.png)

GameEngine package contains TerminalUIEngine implements UIEngine. It also contains predefined Colors for TerminalUIEngine.(ANSI standards)

#### Model


**Observer Pattern**

Every model property has a observer set. The set contains all viewmodels hooked for this property. Once the property has been changed(`Set()`), the hook set will be iteratored.

* single line properties

![](./readme/modelproperty0.png)

A single line property means the property's get() method could only get a iterator which contains one value.

* Multiline properties

![](./readme/listproperty.png)


A multiline property means could be outputed to view in multilines. (This not means it must be outputed in multilines. `GameView` will only output the first line if a block is defined as a oneline block. (See `view render` following)

**the list properties could not be changed by set(String value) method**
This method will call notify the property changed handlers only.
use `set(List<Object> list)` 
or 
modify the origional container and then call this method for sending a notification.

* Model 

![](./readme/modeldemo.png)

This is a simple Model example. The `GameViewModel` will automaticlly bind each member in a model to a fields(which has a same name) in the view.

#### UIFactory

**Factory Pattern**

![](./readme/UIFactory.png)

You need to use this `GameUIComponmentFactory` to generate your GameUIComponment.
Instant your Model, ViewModel and View, then use the factory to generate your GameUIComponment.

### ViewModel

See`view render`

![](./readme/VVM.png)


## View Instructions & View Token & View Render
```
  __  __             
 |  \/  | __ _ _ __  
 | |\/| |/ _` | '_ \ 
 | |  | | (_| | |_) |
 |_|  |_|\__,_| .__/ 
              |_|    
------------------------------------
[e:elements]
<{{property1}},color=(color)>
[e2:elements]
<(e2),color=RED> 
[endfor]
[endfor]

<(oneline_demo0),color=RED,max,min>  <(oneline_demo1),color=RED> 
<{{multiline}},color=RED,max=20>  <(multiline),color=RED> 
<{{multiline}},color=RED,min=30>  <{{multiline_long}},color=RED> 
```

This is a demo view. Contains all token we already have.
### Static Contents
```
  __  __             
 |  \/  | __ _ _ __  
 | |\/| |/ _` | '_ \ 
 | |  | | (_| | |_) |
 |_|  |_|\__,_| .__/ 
              |_|    
------------------------------------
```
The static contents will not be changed in the render process
### element token
```
<{{property1}},color=(color)>
```
A element token will contains values by `,` and the first value must be a Singleline or a  Multiline Block. Element token could define the text color in the Singleline or a  Multiline Block, the maxium length of the output of this token by using `max` and `min`.

color could also be a dynamic singleline block.

#### color 
Possible value:
```
WHITE
RED
GREEN
YELLOW
BLUE
PURPLE
CYAN
```

case sensitivity: NO

Must has a value: YES.

if set a illegal color value, view render will use the default color.

#### max

`max=10` set the maxium length of charactor for block's one line output(in this case, 10 characters). The render will only count the final output for a element token, this means settings(color setting,max setting, min setting) will not be counted in the length. 

**Except you only use the `max`**, in this case the max length will equals length of this element token.

Render will cut from beginning if length exceeded.

case sensitivity: Must a number

Must has a value: NO


#### min


`min=10` set the minimum length of charactor for block's one line output(in this case, 10 characters). The render will only count the final output for a element token, this means settings(color setting,max setting, min setting) will not be counted in the length. 

**Except you only use the `min`**, in this case the min length will equals length of this element token.


Render will add blank to the end.

case sensitivity: Must a number

Must has a value: NO

### Multiline Block
```
<{{property1}},color=(color)>
```

property: property1

Render will iterate the property1 and use the style setting in the `element token`v to all lines.

If you have more than one multiline block in one line, The render will iterater them together until all property's iterators don't have more elements. Render will try to use less lines to do the iterations. If the length of blocks are different, render wont add blanks automaticlly. See `min` to keep your view pattern.

### Singleline Block
```
<{{property1}},color=(color)>
```

property: color

Render will get the first result of color property's iterator.

### Iteration token
```
[e2:elements]
<(e2),color=RED> 
[endfor]
```
A typical Iteration block.

Render will iterating the `elements` modelproperty and the value of every interating loop will be `e2`.

If elements contains `{"element0","element1","element2" }`
The output should be 
```
element0 
element1 
element2 
```
(color dosen't show)

**Every element in that block will be copied on iterating**
* `elements = {"element0","element1","element2" }`
* `property1 = "this is a string property"`
* `color = "green"`
```
[e:elements]
<{{property1}},color=(color)>
[e2:elements]
<(e2),color=RED> 
[endfor]
[endfor]
```
The output should be:
```
this is a string property
element0 
element1 
element2 
this is a string property
element0 
element1 
element2 
this is a string property
element0 
element1 
element2 
```
### Overall output
* `elements = {"element0","element1","element2" }`
* `property1 = "this is a string property"`
* `color = "green"`
* `oneline_demo0 = "context_oneline0"`
* `oneline_demo1 = "context_oneline1"`
* `multiline_long = {"element0","element1","element2","element3" }`
```
  __  __             
 |  \/  | __ _ _ __  
 | |\/| |/ _` | '_ \ 
 | |  | | (_| | |_) |
 |_|  |_|\__,_| .__/ 
              |_|    
------------------------------------
this is a string property
element0 
element1 
element2 
this is a string property
element0 
element1 
element2 
this is a string property
element0 
element1 
element2 

context_oneline0                    context_oneline1 
element0  element0 
element1  element0 
element2  element0 
element0                        element0 
element1                        element1 
element2                        element2 
                                element3 
```
(color doesn't show)

![](./readme/terminal.png)

### GameUIComponment
GameUIComponment is a core class of the Terminal Engine. Just like web pages on web browser, GameUIComponments is a similar concept as web pages. 
UIEngine maintains a GameUIComponment stack. The top of stack is the Componment currently showing. 
#### Show()
if you show a componment, this means you will add the componment to the top of the stack, or move this componment in the stack to the top.
#### close()
if you show a componment, this means you will remove the componment from stack.

### TerminalUIEngine
TerminalUIEngine interact with the console IO streaming.
#### Terminal Refresh
The TerminalUIEngine will creade a new thread refresh the console output in 20hz.
####  String requestInput(String message,String ... options)
The method requests a input from user. 
`message` defines message shown at the bottom
`options` defines correct inputs
The Engine will show the message at the bottom, and also shows the permitted input, for example
```
test for an input(a,b,c)
```
^                 ^
message           options

**due to java limitation, we could give a feedback of what user has typed.**

This method will return until user input a correct string.(a string contains in options )

#### showMessage(String m)

This method will show a notification at the bottom. It will stay there for about 3 seconds.
