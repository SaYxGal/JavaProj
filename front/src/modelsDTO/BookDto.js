export default class BookDto {
    constructor(data) {
        this.id = data?.id;
        this.name = data?.name || '';
        this.description = data?.description || '';
        this.author = data?.author;
        this.genres = data?.genres;
    }
}