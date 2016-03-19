#include <iostream>
#include <iomanip>
#include <cstdlib>
#include "HLList.h"

using namespace std;

void printMenu(){
	//function prints menu to console
	cout << "Would you like to:" << endl << endl;
	cout << "1.) Reverse the list" <<endl;
	cout << "2.) Append a character to the list" << endl;
	cout << "3.) Insert a character to the list (in sorted order)" << endl;
	cout << "4.) Insert a character to the list at a given position" << endl;
	cout << "5.) Delete a character from the list" << endl;
	cout << "6.) Quit" << endl;
}
int takeInput(){
	//takes an int from console, if less than 1 or greater than 6 throws exception
	int input;
	cin >> input;
	if(input < 1 || input > 6){
		string exceptionString = "Error: invalid input";
		throw exceptionString;
	}
	else
		return input;
}

int main(){
	int listsize;
	charList cList;
	char input;
	int pos;
	int menuOption = 0;

	//takes listsize from user
	cout << "How big of a linked list would you like to make?" <<endl;
	cin >> listsize;

	//takes characters from user to initialize linked list
	for(int i = 0; i < listsize; i++){
		cout << "Give me a character to place in position " << i << " of the list: " <<endl;
		cin >> input;
		cList.appendChar(tolower(input));
	}
		//outputs completed list to console
		cout << "Your filled list is: " << endl;
		cList.printList();

	//keeps presenting user with menu until they select option 6
	//if they select an out of bounds option catch statement restarts main
	while(menuOption != 6){
		printMenu();
		try{
			menuOption = takeInput();
				switch(menuOption)
				{
				case 1: cout << "List before reversal: " << endl;
					cList.printList();
					cout << "List after reversal: " << endl;
					cList.reverseList();
					cList.printList();
					break;
				case 2: cout << "What character would you like to append to the list?" << endl;
					cin >> input;
					cout << "List before appending: " << endl;
					cList.printList();
					cList.appendChar(tolower(input));
					cout << "List after appending: " << endl;
					cList.printList();
					break;
				case 3: cout << "What character would you like to insert into the list?" << endl;
					cin >> input;
					cout << "List before inserting: " << endl;
					cList.printList();
					cList.insertChar(tolower(input));
					cout << "List after inserting: " << endl;
					cList.printList();
					break;
				case 4:cout << "What character would you like to insert into the list?" << endl;
					cin >> input;
					cout << "At what position would you like to insert it?" << endl;
					cin >> pos;
					cout << "List before inserting: " << endl;
					cList.printList();
					cList.insertCharAtPos(tolower(input), pos);
					cout << "List after inserting: " << endl;
					cList.printList();
					break;
				case 5: cout << "What character would you like to delete from the list?" << endl;
					cin >> input;
					cout << "List before deleting: " << endl;
					cList.printList();
					cList.deleteNode(tolower(input));
					cout << "List after deleting: " << endl;
					cList.printList();
					break;
				case 6: cout << endl;
					cout << "Exiting" << endl;
					break;
				}
		}
		catch(string exceptionString){
			cout << exceptionString << endl;
			main();
		}
	}
	return 0;
}
