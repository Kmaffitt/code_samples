#include "HLList.h"
#include <iostream>
#include <iomanip>
#include <cstdlib>

using namespace std;

charList::charList(){
	//if the charList constructor is being called there is no head yet
	head = NULL;
}
charList::charList(const charList &obj){
	//copy constructor
	Node* nodePtr;
	Node* newNode;

	if(obj.head == NULL){
		this->head = NULL;
		return;
	}
	else
	{
		nodePtr = obj.head;
		while(nodePtr){
			newNode = nodePtr;
			nodePtr = obj.head->next;
		}
	}
}
charList::~charList(){
	Node* nodePtr = head;
	Node* prevNode;
	while(nodePtr){
		prevNode = nodePtr;
		nodePtr = nodePtr->next;
		delete prevNode;
	}
}
void charList::appendChar(char c){
	Node* newNode; //points to new node
	Node* nodePtr; //iterator

	//initialize new node with passed in value, set next pointer to Null
	//as this new node is currently the last node in the list
	newNode = new Node;
	newNode-> value = c;
	newNode-> next = NULL;

	//if there is no head this is the first node in a new charList
	if(!head)
		head = newNode;
	else
	{
		//point iterator at start of list
		nodePtr = head;
		//iterate through list
		while(nodePtr-> next)
		{
			nodePtr = nodePtr-> next;
		}

		//when we hit end of list append our new node to end of chain
		nodePtr->next = newNode;
	}
}
void charList::insertChar(char c){
	//this function inserts a char node into a linked list in sorted order
	Node* newNode; //points to new node
	Node* prevNode; //points to previous node
	Node* nodePtr; //iterator

	//initialize new node with passed in value
	newNode = new Node;
	newNode-> value = c;

	//if there is no head this is the first node in the list
	if(!head){
		head = newNode;
		newNode->next= NULL;
	}
	else
	{
		nodePtr=head;//point at start of list
		prevNode = NULL; //init prevNode

		//iterate over list until we hit end or node with value > new value
		while(nodePtr != NULL && nodePtr->value < c){
			prevNode = nodePtr;
			nodePtr = nodePtr->next;

		}if(prevNode == NULL){
			head = newNode;
			newNode->next = nodePtr;
		}
		else
		{
			prevNode->next = newNode;
			newNode->next = nodePtr;
		}
	}
}
void charList::printList(){
	Node* nodePtr; //iterator

	nodePtr= head; //point at start of list

	//traverse list printing node's values
	while(nodePtr){
		cout << "[" << nodePtr->value << "] ";//print value

		nodePtr= nodePtr-> next;
	}
	cout << endl << endl;
}
void charList::deleteNode(char c){
	Node* nodePtr; //interator
	Node* prevNode; // points to previous node

	//if the list is empty there is nothing to delete
	if(!head){
		cout << "Nothing to delete" << endl;
		return;
	}

	if(head->value == c)
	{
		nodePtr = head->next;
		delete head;
		head = nodePtr;
	}
	else
	{
		//point at start of list
		nodePtr = head;
		//traverse list skipping nodes who's value != c
		while(nodePtr != NULL && nodePtr-> value != c){
			prevNode = nodePtr;
			nodePtr = nodePtr->next;
		}
		if(nodePtr)
		{
			prevNode->next= nodePtr->next;
			delete nodePtr;
		}
	}
}
void charList::reverseList(){
	Node* nodePtr; // iterator
	int listsize = 0;

	// if there is no head, can't reverse list
	if(!head)
	{
		cout << "Can't reverse an empty list" <<endl;
		return;
	}

	//get list size
	nodePtr = head;
	while(nodePtr!= NULL){
		nodePtr = nodePtr->next;
		listsize++;
	}

	//copy list values to array
	nodePtr = head;
	char valueArray[listsize];
	for (int i = 0; i < listsize; i++){
		valueArray[i] = nodePtr->value;
		nodePtr = nodePtr->next;
	}

	nodePtr = head;
	//copy values back into array in reverse order
	for (int i = listsize-1; i > -1; i--){
		nodePtr->value = valueArray[i];
		nodePtr = nodePtr->next;
	}

}
void charList::insertCharAtPos(char c, int pos){
	Node* newNode; //points to new node
	Node* nodePtr; //iterator
	Node* prevNode; // points at previous node

	//initialize new node with passed in value
	newNode = new Node;
	newNode-> value = c;


	//if there is no head this is the first node in a new charList
	if(!head || pos == 0){
		nodePtr= head;
		head = newNode;
		head->next = nodePtr;
		return;
	}
	else
	{
		//point iterator at start of list
		nodePtr = head;
		//iterate to position, keeping track of previous node
		for(int i = 0; i < pos; i++){
			prevNode = nodePtr;
			nodePtr = nodePtr->next;
		}
		//insert new node into list
		prevNode->next = newNode;
		newNode->next = nodePtr;
	}
}