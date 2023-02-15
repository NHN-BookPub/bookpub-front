const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '500px',
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    toolbarItems: [
        ['heading', 'bold'],
        ['ul', 'ol', 'task'],
        ['code', 'codeblock'],
    ],
    codeBlockLanguages: ['ruby', 'PHP', 'javascript']
});


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