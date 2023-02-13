const Editor = toastui.Editor;

const viewer = toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true
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


