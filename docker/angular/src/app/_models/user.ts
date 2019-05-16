import { JsonObject, JsonProperty } from 'json2typescript';

/**
 * Class that strores info about registered user
 * @author Alexander Zhulinsky
 * @version 1.0 07 Aug 2018
 */
@JsonObject('User')
export class User {
  @JsonProperty('username', String)
  private _username: string;
  // @JsonProperty('login', String)
  private _login: string;
  // @JsonProperty('email', String)
  private _email: string;
  // @JsonProperty('password', String)
  private _password: string;
  @JsonProperty('jwt', String)
  private _token: string;

  constructor(
    username?: string,
    login?: string,
    email?: string,
    password?: string,
    token?: string) {
      this._username = username || void 0;
      this._login = login || void 0;
      this._email = email || void 0;
      this._password = password ||  void 0;
      this._token = token ||  void 0;
    }

  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }
  public get username(): string {
    return this._username;
  }
  public set username(value: string) {
    this._username = value;
  }
  public get password(): string {
    return this._password;
  }
  public set password(value: string) {
    this._password = value;
  }
  public get login(): string {
    return this._login;
  }
  public set login(value: string) {
    this._login = value;
  }

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
  }

  public isEmpty(): boolean {
    return (this._username == null && this._token == null);
  }
}
