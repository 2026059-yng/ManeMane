//idから要素を取得（ー使う、＋増やすボタン）
const buttonA = document.getElementById('home-out-button');
const buttonB = document.getElementById('home-in-button');
const buttonC = document.getElementById('home-submit-button');

//idから要素を取得（カテゴリーごと）
const buttonD = document.getElementById('home-category1');
const buttonE = document.getElementById('home-category2');
const buttonF = document.getElementById('home-category3');

document.addEventListener("DOMContentLoaded", () => {

    //カレンダーの初期値を当日に設定
    const currentDate = new Date();
    const y = currentDate.getFullYear();
    const m = String("0" + (currentDate.getMonth() + 1)).slice(-2);
    const d = String("0" + currentDate.getDate()).slice(-2);
    document.getElementById("dateValue").value = y + '-' + m + '-' + d;


    //『ー使う』ボタンをデフォルトに設定
    buttonA.classList.add('active');

    //in_outフィールドに送る値のデフォルトを true（ー使う）に設定
    document.getElementById("in_out").value = 'true';

    //category_nameフィールドに送る値のデフォルトを 食費 に設定
    document.getElementById("home-category_name").value = '食費';

    //カレンダーに今月のみ入力できるように設定
    const input = document.getElementById('dateValue');

    //今月の1日
    input.min = y + '-' + m + '-' + '01';
    //今月の末日
    const lastDay = new Date(y, currentDate.getMonth() + 1, 0).getDate();
    input.max = y + '-' + m + '-' + lastDay;

})

//ボタン押下で true(ー使う) / false(＋増やす) をvalueに格納
function selectInOut(selectInout) {
    document.getElementById("in_out").value = selectInout;
}

//押下されたカテゴリーにcssをセット
function selectCategory(category_num) {
    if (category_num == "1") {
        document.getElementById("home-category_name").value = document.getElementById('home-category1').value;
    } else if (category_num == "2") {
        document.getElementById("home-category_name").value = document.getElementById('home-category2').value;
    } else {
        document.getElementById("home-category_name").value = document.getElementById('home-category3').value;
    }
}

//要素を取得（金額ボタン）
let button1 = document.getElementById('home-button1');  //100
let button2 = document.getElementById('home-button2');  //500
let button3 = document.getElementById('home-button3');  //1000
let button4 = document.getElementById('home-button4');  //10000
let button5 = document.getElementById('home-button5');  //金額クリアボタン
let text = document.getElementById('daily_amount');     //金額　テキストボックス

let count = 0;

//表示する金額の計算、表示
function calcAmount(button) {
    if(document.getElementById('daily_amount').value != 0){
        count = parseInt(document.getElementById('daily_amount').value, 10);
    }
    if (button == "button1") {
        count += 100;
    } else if (button == "button2") {
        count += 500;
    } else if (button == "button3") {
        count += 1000;
    } else if (button == "button4") {
        count += 10000;
    } else {
        count = 0;
    }
    document.getElementById("daily_amount").value = count;
}

buttonA.addEventListener('click', function () {   // Aボタンをクリックした時
    buttonA.classList.add('active');
    buttonB.classList.remove('active');
    buttonC.classList.remove('in');
    buttonC.classList.add('out');
});

buttonB.addEventListener('click', function () {   // Bボタンをクリックした時
    buttonA.classList.remove('active');
    buttonB.classList.add('active');
    buttonC.classList.remove('out');
    buttonC.classList.add('in');
});

buttonD.addEventListener('click', function () {   // Dボタンをクリックした時
    buttonD.classList.add('active');
    buttonE.classList.remove('active');
    buttonF.classList.remove('active');
});

buttonE.addEventListener('click', function () {   // Eボタンをクリックした時
    buttonD.classList.remove('active');
    buttonE.classList.add('active');
    buttonF.classList.remove('active');
});
buttonF.addEventListener('click', function () {   // Fボタンをクリックした時
    buttonD.classList.remove('active');
    buttonE.classList.remove('active');
    buttonF.classList.add('active');
});
