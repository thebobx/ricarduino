# Ricarduino
**Ricarduino** is a robot bartender which mixes cocktails for you !

It's a java web application designed to run on a **Raspberry Pi** and control actuators through his **GPIO**.

## Features
* List of cocktails with picture, description, ingredients etc.
* You can choose your type/size of glass
* You can adapt the recipe to your taste
* It automatically define how many times each actuator need to be operated in order to get the right amount of each ingredient of the recipe depending on the glass size

## Description
**Ricarduino** is developed with Cuba Studio and need a PostgreSQL database. It uses the **Pi4J** library to control the GPIO of the Raspberry.
It's designed to control **Linear Actuators** which in turn will push **Bar Optics** to dispense the ingredients.
So each ingredient is linked to an actuator which has GPIO number and optic size properties.

## About the name
At the beginning it was supposed to be a simpler project supposed to dispense only Ricard with an arduino : Ricard(ard)uino.

## Todo
* Generify the type of actuator (pumps instead of optics for example)
* Material design !
