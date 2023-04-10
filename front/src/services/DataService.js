import axios from 'axios';
export default class DataService {
    static async readAll(dataUrlPrefix, url, transformer) {
        const response = await axios.get(dataUrlPrefix + url);
        return response.data.map(item => transformer(item));
    }
    static async readFilteredByGenre(dataUrlPrefix, url, transformer, id){
        const response = await axios.get(dataUrlPrefix + url + `?genreId=${id}`);
        return response.data.map(item => transformer(item));
    }
    static async readFilteredByAuthor(dataUrlPrefix, url, transformer, id){
        const response = await axios.get(dataUrlPrefix + url + `?authorId=${id}`);
        return response.data.map(item => transformer(item));
    }
    static async readFilteredByName(dataUrlPrefix, url, transformer, name){
        const response = await axios.get(dataUrlPrefix + url + `?name=${name}`);
        return response.data.map(item => transformer(item));
    }
    static async read(dataUrlPrefix, url, id, transformer) {
        const response = await axios.get(dataUrlPrefix + url + '/' + id);
        return transformer(response.data);
    }

    static async createBook(dataUrlPrefix, url, data) {
        let genres = "";
        data.genres.forEach(element => genres += `&genreId[]=${element}`);
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.post(dataUrlPrefix + url + `?name=${data.name}&desc=${data.description}
        &authorId=${data.author}` + genres, JSON.stringify(data), headers);
        return true;
    }
    static async createAuthor(dataUrlPrefix, url, data){
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.post(dataUrlPrefix + url + `?name=${data.name}&surname=${data.surname}
        &patronymic=${data.patronymic}`, JSON.stringify(data), headers);
        return true;
    }
    static async createGenre(dataUrlPrefix, url, data){
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.post(dataUrlPrefix + url + `?name=${data.name}`, JSON.stringify(data), headers);
        return true;
    }

    static async updateBook(dataUrlPrefix, url, data) {
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.put(dataUrlPrefix + url + '/' + data.id + `?name=${data.name}&desc=${data.description}
        &authorId=${data.author}`, JSON.stringify(data), headers);
        return true;
    }
    static async updateAuthor(dataUrlPrefix, url, data) {
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.put(dataUrlPrefix + url + '/' + data.id + `?name=${data.name}&surname=${data.surname}
        &patronymic=${data.patronymic}`, JSON.stringify(data), headers);
        return true;
    }
    static async updateGenre(dataUrlPrefix, url, data) {
        const headers = { 
            "Content-Type": "application/json"
        };
        const response = await axios.put(dataUrlPrefix + url + '/' + data.id + `?name=${data.name}`, JSON.stringify(data), headers);
        return true;
    }
    static async updateBookGenre(dataUrlPrefix, url, data, genreId, param){
        const headers = { 
            "Content-Type": "application/json"
        };
        if(param === true){
            const response = await axios.put(dataUrlPrefix + url + '/' + data.id + '/genres/' + genreId, JSON.stringify(data), headers);
        }
        else{
            const response = await axios.post(dataUrlPrefix + url + '/' + data.id + '/genres/' + genreId, JSON.stringify(data), headers);
        }
        return true;
    }

    static async delete(dataUrlPrefix,url, id) {
        const response = await axios.delete(dataUrlPrefix + url + '/' + id);
        return response.data.id;
    }
}