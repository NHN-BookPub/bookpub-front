const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '500px',
    initialEditType: 'markdown',
    previewStyle: 'vertical'
    // codeBlockLanguages: ['ruby', 'PHP', 'javascript'],
});

var imagePathList = [];
let imagePath;
// tui editor 이미지 저장 함수
editor.addHook("addImageBlobHook", function (blob, callback) {
    const formData = new FormData();
    formData.append('image', blob);

    $.ajax({
        url: "/inquiries/image/save",
        type: "post",
        async: true,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        data: formData,
        success: function (response) {
            let tmpText = "";
            imagePath = response;
            callback(imagePath);
            imagePathList.push(imagePath);
            imagePathList.forEach((path) => {
                tmpText += (path + "$");
            })
            document.getElementById("imagePathList").value = tmpText;
            console.log(document.getElementById("imagePathList").value);
        }
    })
})

function checkInfo() {
    if (editor.getMarkdown() == null) {
        alert("내용을 입력해주세요.");
        console.log("내용없음");
        return false;
    } else {
        let hiddenDes = document.getElementById("hiddenDesc");
        hiddenDes.textContent = editor.getMarkdown();

        return true;
    }
}
