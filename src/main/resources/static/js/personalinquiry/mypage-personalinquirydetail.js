const Editor = toastui.Editor;

const viewer = toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true
});
viewer.setMarkdown(document.getElementById('question-hidden').textContent);


const answerViewer = toastui.Editor.factory({
        el: document.querySelector('#answerViewer'),
        viewer: true,
        height: '500px'
    });
answerViewer.setMarkdown(document.getElementById("answerContent").textContent);


