
const cloudservice = (() =>{

    const poolData = (callback) =>{
        setInterval(() => {
            axios.get('/stats',)
                .then( (res) =>{
                    console.log("Hola");
                    callback(res);
                });
        }, 5000);
    }


    return{
        poolData:poolData
    }
})();
