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
viewer.setMarkdown(document.getElementById('question-hidden').textContent);

const answerViewer = toastui.Editor.factory({
    el: document.querySelector('#answerViewer'),
    viewer: true,
    height: '500px'
})
answerViewer.setMarkdown(document.getElementById('answer').textContent);

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


