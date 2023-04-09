export default class AuthorDto {
    constructor(data){
        this.id = data?.id;
        this.name = data?.name || '';
        this.surname = data?.surname || '';
        this.patronymic = data?.patronymic || '';
        this.books = data?.books;
    }
}