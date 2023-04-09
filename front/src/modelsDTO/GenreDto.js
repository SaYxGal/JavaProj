export default class GenreDto {
    constructor(data) {
        this.id = data?.id;
        this.name = data?.name || '';
    }
}