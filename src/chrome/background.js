'use strict';

const apiUrl = "http://localhost:82/get-notes"

chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab) {
    if (changeInfo.status == 'complete' && tab.active) {
        let url = apiUrl + "?url=" + tab.url;
        fetch(url)
            .then(response => response.json())
            .catch(error => console.log(error))
            .then(jsonResponse => {
                console.log(jsonResponse)

                var result = ""
                jsonResponse.forEach(note => {
                    result += `\n${note.status} ${note.note}`
                });

                alert(result)
            });
    }
});