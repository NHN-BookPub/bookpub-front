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

// // tui editor 이미지 저장 함수
// editor.addHook("addImageBlobHook", function(callback){
//
//     // !!!!! 여기서 이미지를 받아와서 이미지 주소를 받아오고 (ajax 등으로)
//     // callback의 인수로 넣으시면 됩니다.
//     callback("콜백으로 받아온 이미지 URL")
// })
// // 텍스트 가지고 오기
// console.log(editor.getMarkdown())


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


