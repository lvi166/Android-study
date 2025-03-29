//
// Created by Administrator on 2025/3/29.
//
#include <stdio.h>
#include "data_stract.h"

struct Books
{
    char  title[50];
    char  author[50];
    char  subject[100];
    int   book_id;
} books = {"C 语言", "RUNOOB", "编程语言", 123456};
  

int main(){

    arrayData();

     return 0;

}

void arrayData(void){
    int arr[5] = {10, 20, 30, 40, 50};
    int *ptr = arr ;
    

    // int *ptr = &myArray[0]; // 或者直接写作 int *ptr = myArray;

   /*  在上面的例子中，ptr 指针变量被初始化为 arr[] 的地址，即数组的第一个元素的地址。
       需要注意的是，虽然数组名表示数组的地址，但在大多数情况下，
       数组名会自动转换为指向数组首元素的指针。
       这意味着我们可以直接将数组名用于指针运算，例如在函数传递参数或遍历数组时：
   */

      for (int i = 0; i < LENGTH(arr); i++) {
        printf("%d ",*(ptr+i) ); // *(ptr+i) 等价于 arr[i]
      }

      printf("%d \n",books.book_id);

}

enum DAY
{
    MON=1, TUE, WED, THU, FRI, SAT, SUN
};

