export default class UserLoginDto {
    constructor(data) {
        this.login = data?.login;
        this.password = data?.password;
    }
}