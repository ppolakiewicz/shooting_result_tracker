export interface UserAuthenticateDto {
  readonly email: string,
  readonly password: string
}

export interface AuthenticationResponseDto {
  readonly token: string
}

export interface UserIdentity {
  readonly userId: number;
  readonly sub: string;
  readonly grantedAuthorities: string[];
  readonly active: boolean;
  readonly exp: number;
  readonly iat: number;
}
