export default class UserSignUpDto {
    constructor(data) {
        this.login = data?.login;
        this.password = data?.password;
        this.passwordConfirm = data?.passwordConfirm;
    }
}