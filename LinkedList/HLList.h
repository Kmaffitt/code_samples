#ifndef HLLIST_H
#define HLLIST_H

using namespace std;

class charList{
private:

	struct Node
	{
	//node's stored data
	char value;
	//pointer to next node in chain
	struct Node* next;
	};

	//pointer to first node in chain
	Node* head;

public:

	charList();//
	charList(const charList &obj);
	~charList();//
	void appendChar(char);//
	void insertChar(char);//
	void deleteNode(char);
	void printList();//
	void reverseList();//
	void insertCharAtPos(char, int);//

};
#endif