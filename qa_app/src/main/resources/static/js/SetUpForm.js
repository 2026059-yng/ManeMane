// 未経験の方へ
// 収入と固定費の入力欄を動的に追加・削除し、使用可能金額をリアルタイムで計算して表示します
// 収入が固定費を下回る場合はエラー表示を行い、送信時に入力内容のバリデーションも行います
// フラグとは、特定の条件が満たされたかどうかを示すための変数で、ここでは無効な固定費があるかどうかを示すために使用されています
// 例を挙げると、固定費の名前が空であったり、金額が0以下であったりする場合にフラグを立てることで、送信時にエラーを表示することができます
// hasInvalidFixedCostというフラグは、固定費の名前や金額が不正な場合はtrueに設定され、送信時にエラーを表示するために使用されます
// 和訳すると「無効な固定費が存在するか？」のような意味になるそうです。

// 使用可能金額を計算して表示
function updateAvailableAmount() {
    const income = Number(document.getElementById("incomeAmount").value) || 0;

    let totalFixed = 0;

    // 固定費の合計を計算
    document.querySelectorAll(".fixedAmount").forEach(fixid => {
        const amount = Number(fixid.value) || 0;
        totalFixed += amount;
    });

    const available = income - totalFixed;

    // 使用可能金額表示要素とエラー表示要素を取得
    const availableAmount = document.getElementById("available_amount");
    const availableError = document.getElementById("availableError");

    // 使用可能金額表示
    availableAmount.textContent = available.toLocaleString();

    // マイナスなら赤文字＋エラー表示
    if (available < 0) {
        availableAmount.style.color = "red";
        availableError.style.display = "block";
    } else {
        availableAmount.style.color = "";
        availableError.style.display = "none";
    }
}

// 収入入力欄は半角数字のみ入力可能にする
document.querySelectorAll(".numberOnly").forEach(input => {
    input.addEventListener("input", function () {

        this.value = this.value
            .replace(/[０-９]/g, s => String.fromCharCode(s.charCodeAt(0) - 0xFEE0))
            .replace(/[^0-9]/g, "");
        updateAvailableAmount();// 収入が変更されたら使用可能金額を更新
    });
});

// 固定費追加
document.getElementById("addFixedCost").addEventListener("click", () => {

    // 固定費入力欄を追加するエリアを取得
    const area = document.getElementById("fixedCostArea");

    // 新しい固定費入力欄を作成
    const div = document.createElement("div");

    // 固定費の入力欄を追加
    div.classList.add("fixedCost");

    // 固定費の名前と金額、削除ボタンを追加
    div.innerHTML = `
    <input type="text" class="fixedName" placeholder="固定費名">
    <input type="text" class="fixedAmount" placeholder="金額">
    <button type="button" class="deleteBtn">削除</button>`;

    const amountInput = div.querySelector(".fixedAmount");

    // 固定費の金額入力欄も半角数字のみ入力可能にする
    amountInput.addEventListener("input", function () {

        this.value = this.value
            .replace(/[０-９]/g, s =>
                String.fromCharCode(s.charCodeAt(0) - 0xFEE0)
            )
            .replace(/[^0-9]/g, "");

        updateAvailableAmount();

    });


    // 削除時に再計算
    div.querySelector(".deleteBtn")
        .addEventListener("click", () => {
            // 削除ボタンが押されたらその固定費入力欄を削除
            div.remove();
            updateAvailableAmount();
        });
    // 追加した固定費入力欄をエリアに追加
    area.appendChild(div);
});


// 送信処理
document.getElementById("userForm")
    .addEventListener("submit", function (event) {

        // 収入が入力されているかチェック
        const incomeValue = document.getElementById("incomeAmount").value.trim();

        // 収入が空の場合はアラートを表示して送信を中止
        if (!incomeValue) {
            alert("収入を入力してください。");
            event.preventDefault();
            return;
        }

        const incomeAmount = Number(incomeValue);// 収入を数値に変換

        // 固定費の合計を計算
        let totalFixed = 0;

        // 固定費の名前と金額を格納する配列
        const fixedNames = [];
        const fixedAmounts = [];

        // 無効な固定費があるかどうかのフラグ
        let hasInvalidFixedCost = false;

        // 固定費の入力欄をループして合計を計算
        document.querySelectorAll(".fixedCost").forEach(fixid => {

            // 固定費の名前と金額を取得
            const name = fixid.querySelector(".fixedName").value.trim();
            const amountText = fixid.querySelector(".fixedAmount").value.trim();

            // 名前または金額が空の場合は無効な固定費としてフラグを立てる
            // 金額が0以下の場合も無効な固定費としてフラグを立てる
            if (!name || !amountText || Number(amountText) <= 0) {
                hasInvalidFixedCost = true;
                return;
            }

            // 金額を数値に変換
            const amount = Number(amountText);

            // 金額が数値でない場合も無効な固定費としてフラグを立てる
            // 必要はないが念のため、数値でない場合もチェックしておく
            if (isNaN(amount)) {
                hasInvalidFixedCost = true;
                return;
            }

            totalFixed += amount;
            fixedNames.push(name);// 固定費の名前を配列に追加
            fixedAmounts.push(amount); // 固定費の金額を配列に追加
        });

        // 無効な固定費がある場合はアラートを表示して送信を中止
        if (hasInvalidFixedCost) {
            alert("固定費の名前と金額をすべて入力してください。");
            event.preventDefault();
            return;
        }

        // 使用可能金額チェック
        if (incomeAmount - totalFixed < 0) {
            alert("固定費が収入を超えているため登録できません。");
            event.preventDefault();// フォームの送信をキャンセル
            return;// 送信を中止してエラーを表示
        }

        // カテゴリの値を取得
        const cat1 = document.getElementById("cat1").value.trim();
        const cat2 = document.getElementById("cat2").value.trim();
        const cat3 = document.getElementById("cat3").value.trim();

        // カテゴリがすべて入力されているかチェック
        if (!cat1 || !cat2 || !cat3) {
            alert("カテゴリはすべて入力してください。");
            event.preventDefault();
            return;
        }

        const categories = [cat1, cat2, cat3];

        // すべてのデータをオブジェクトにまとめる
        const allData = {
            incomeAmount,
            categories,
            fixedNames,
            fixedAmounts
        };

        // JSON文字列に変換してhiddenフィールドにセット
        document.getElementById("hiddenAllData").value =
            JSON.stringify(allData);
    });