# Калькулятор
Это оригинальная версия калькулятора. Версию с парсером на ANTLR вы можете увидеть в репозитории [mycalculator-antlr](https://github.com/dmitriyushakov/mycalculator-antlr).
Приложение позволяет вычислять числовые значения на основе введенного приложения. Вводимые и вычисленные значения определены, используя [java BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html). Программа запускается в интерактивном режиме, ожидает ввода выражения пользователем и после этого производит значения этого выражения. Поддерживаются приоритет операторов и круглые скобки "(" и ")" в качестве управления вложенностью выражений.
Пример вычисления простого выражения (знак ">" в первой строке обозначает приглашение ввода выражения):

     > 2*(3+4)
    14

## Работа с переменными
Калькулятор позволяет обращаться к переменным по их имени. Имя переменной обозначается комбинацией символов английского алфавитами и цифрами 0-9. Начало имени не может содержать цифру. Для сохранения значения переменной может использоваться оператор "=".
Пример работы с переменными:

     > var = 5
    5
     > var
    5
     > var*var
    25
     > var=var*10
    50
## Работа с функциями
Калькулятор поддерживает вызов функций для вычисления значений. В отличии от обращения к значению переменной, оператор вызова функции содержит круглые скобки, с перечислением выражений через запятую.
Пример вызова функции:

     > sin(pi/2)
    1
Кроме этого, калькулятор поддерживает определение функции при помощи оператора "=".
Пример определения функции:

     > fall(h,t)=max(0,h-t*t*9.8)
    0
     > fall(100,2)
    60.8
## Операторы сравнения
Калькулятор поддерживает операторы для сравнения значений - равно(==), больше(>), меньше(<), больше или равно(>=), меньше или равно(<=). В случае истинности оператор вернет 1, в случае ложности 0.
Пример использования операторов сравнения:

     > 3>2
    1
     > 3<2
    0
     > 2==2
    1
     > 2==3
    0
## Логические функции
В окружении калькулятора определены логические функции, которые позволяют комбинировать значения многих логических выражений. Число 0 считается ложным значением. Остальные - истинными.
Функция not(не) принимает один аргумент. Функции or(или), and(и) произвольное число аргументов, но не менее двух.
Пример использования логических функций:

     > or(2>3,2<3)
    1
     > and(2>3,2<3)
    0
     > not(0)
    1
     > not(1)
    0
## Тернарный оператор
В калькуляторе реализован тернарный оператор (<условие>?<значение при истинном условии>:<значение при ложном значении>) для выбора вычисляемого значения в зависимости от условия.
Пример использования тернарного оператора

     > 3>2?10:20
    10
     > 3<2?10:20
    20
Тернарный оператор может использоваться в качестве условия для выхода из рекурсии:

     > fib(n)=n<=2?1:(fib(n-2)+fib(n-1))
    0
     > fib(1)
    1
     > fib(2)
    1
     > fib(3)
    2
     > fib(5)
    5
     > fib(7)
    13
     > fib(15)
    610
## Режим отладки дерева выражения
Калькулятор может быть переведен в режим, в котором он выполняет построение дерева операций путем вызова функции enableAstDebug().
Пример построения дерева:

     > enableAstDebug()
    1
     > 1+2*(5-3)
    PlusOperator
        Number (1)
        MultiplyOperator
            Number (2)
            MinusOperator
                Number (5)
                Number (3)
