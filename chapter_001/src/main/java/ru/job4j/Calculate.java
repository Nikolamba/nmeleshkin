package ru.job4j;

/**
* Простой класс для вывода в консоль
* строки Hello World
*/
class Calculate {
/**
* Главный метод программы, содержащий функцию
* вывода информации на консоль
*/
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
    		return "Echo, echo, echo : " + name;
	}
}