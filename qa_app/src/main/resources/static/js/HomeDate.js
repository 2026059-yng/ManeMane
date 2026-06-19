document.addEventListener("DOMContentLoaded", () => {
    const currentDate = new Date();
    const y = currentDate.getFullYear();
    const m = String("0" + (currentDate.getMonth() + 1)).slice(-2);
    const d = String("0" + currentDate.getDate()).slice(-2);
    document.getElementById("dateValue").value = y + '-' + m + '-' + d;


})

function selectInOut(selectInout) {
    document.getElementById("in_out").value = selectInout;
}


function selectCategory(category_num) {
    if(category_num == "1"){
        document.getElementById("category_name").value = document.getElementById('category1').value;
    }else if(category_num == "2"){
        document.getElementById("category_name").value = document.getElementById('category2').value;
    }else{
        document.getElementById("category_name").value = document.getElementById('category3').value;
    }
}

// 1. 部品の取得
let button1 = document.getElementById('button1');
let button2 = document.getElementById('button2');
let button3 = document.getElementById('button3');
let button4 = document.getElementById('button4');
let button5 = document.getElementById('button5');
let text = document.getElementById('daily_amount');

// 2. データを記憶するポケットの作成
let count = 0;

function calcAmount(button) {
    if(button == "button1"){
        count += 100;
    }else if(button == "button2"){
        count += 500;
    }else if(button == "button3"){
        count += 1000;
    }else if(button == "button4"){
        count += 10000;
    }else{
        count = 0;
    }
    document.getElementById("daily_amount").value = count;
}

// button1.addEventListener('click', () => {
//   // ポケットの数値を更新
//   count = count + 100;
//   // 画面の表示を更新
//   text.textContent = count;
// });

// button2.addEventListener('click', () => {
//   // ポケットの数値を更新
//   count = count + 100;
//   // 画面の表示を更新
//   text.textContent = count;
// });

// button3.addEventListener('click', () => {
//   // ポケットの数値を更新
//   count = count + 100;
//   // 画面の表示を更新
//   text.textContent = count;
// });

// button4.addEventListener('click', () => {
//   // ポケットの数値を更新
//   count = count + 100;
//   // 画面の表示を更新
//   text.textContent = count;
// });

// button5.addEventListener('click', () => {
//   // ポケットの数値を更新
//   count = 0;
//   // 画面の表示を更新
//   text.textContent = count;
// });