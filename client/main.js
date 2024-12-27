// YOUR JAVASCRIPT CODE FOR INDEX.HTML GOES HERE
async function testCall(){
    var config = {
        "method": "POST",
        "args": {
        }
    };
    var functions = catalyst.function;
    var functionObject = functions.functionId("testFunction"); //can pass Function Id or Function Name as argument
    var functionPromise = functionObject.execute(config); await functionPromise;
    functionPromise
        .then((response) => {
            response.json().then(responseBody => {
                console.log(responseBody);
            });
        })
        .catch((err) => {
            console.log(err);
        });
    return "completed";
}