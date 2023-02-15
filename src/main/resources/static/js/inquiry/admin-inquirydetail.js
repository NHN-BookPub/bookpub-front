//const Editor = toastui.Editor;

//import Editor from '@toast-ui/editor';

const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    height: '300px'
});

const viewer = toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true,
    height: '500px'
});

let question = document.getElementById('question-hidden');
viewer.setMarkdown(question.textContent);

let childGroup = document.getElementsByClassName('viewerGroup');

for (let i = 0; i < childGroup.length; i++) {
    window['viewer' + i] = toastui.Editor.factory({
        el: document.getElementById('viewer' + i),
        viewer: true,
        height: '500px'
    })
    window['viewer' + i].setMarkdown(document.getElementById('childContent' + i).textContent);
}

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


