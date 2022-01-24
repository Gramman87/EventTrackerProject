export class User {
  id: number;
  email: string;
  password: string;
  firstName: string | undefined;
  lastName: string | undefined;

  constructor(
    id: number = 0,
    email: string = "",
    password: string = "",
    firstName?: string,
    lastName?: string
) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
