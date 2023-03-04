export interface UserAuthenticateDto {
  readonly email: string,
  readonly password: string
}

export interface AuthenticationResponseDto {
  readonly token: string
}

export interface UserIdentity {
  readonly id: number;
  readonly username: string;
  readonly role: string;
  readonly active: boolean;
  readonly exp: number;
  readonly iat: number;
}
