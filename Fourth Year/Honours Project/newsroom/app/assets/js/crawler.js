const fs = require('fs');
const got = require('got');
const jsdom = require("jsdom");
const { JSDOM } = jsdom;

function submitURL(url) {
    got(url).then(response => {
        const dom = new JSDOM(response.body);
        console.log(dom.window.document.querySelector('title').textContent);
    }).catch(err => {
        console.log(err);
    });
}
