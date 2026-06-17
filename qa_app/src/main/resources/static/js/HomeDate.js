document.addEventListener("DOMContentLoaded", () => {
    const now = now();
    const y = now.getAllYear;
    const m = String(now.getMonth() + 1).padStart(2,"0");
    //padStartは文字列の左を埋める処理
    //OK 2026-06-01 , NG 2026-6-1
    const d = String(now.getDate()).padStart(2,"0");
    document.getElementById("dateValue").value = '{y}-${m}-${d}';
})
