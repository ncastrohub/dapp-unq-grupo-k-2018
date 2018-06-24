// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
// AGREGADO PARA AUTENTICACION
  auth: {
  	clientID: 'qp5yO35aHhJB42Es5P0m3NSjtcW9bGQo',
    domain: 'tpi-desapp2.auth0.com',
    // responseType: 'token id_token',
    audience: 'https://tpi-desapp2.auth0.com/userinfo',
    redirectUri: 'http://localhost:4200/callback/login',
    // scope: 'openid'

  	
    // domain:       'tpi-desapp.auth0.com',
    // clientID:     'nfJ_xFgdMli9OPzLsnZcfPYLWca-iCBn', // e.g., you.auth0.com
    // audience: 'https://tpi-desapp.auth0.com/api/v2/', // e.g., http://localhost:3001
    responseType: 'token id_token',
    scope: 'openid profile email'
  }
};


export const environmenttwo = {
  production: false,
// AGREGADO PARA AUTENTICACION
  auth: {
  	clientID: 'qp5yO35aHhJB42Es5P0m3NSjtcW9bGQo',
    domain: 'tpi-desapp2.auth0.com',
    // responseType: 'token id_token',
    audience: 'https://tpi-desapp2.auth0.com/userinfo',
    redirectUri: 'http://localhost:4200/callback/sign_up',
    // scope: 'openid'

  	
    // domain:       'tpi-desapp.auth0.com',
    // clientID:     'nfJ_xFgdMli9OPzLsnZcfPYLWca-iCBn', // e.g., you.auth0.com
    // audience: 'https://tpi-desapp.auth0.com/api/v2/', // e.g., http://localhost:3001
    responseType: 'token id_token',
    scope: 'openid profile email'
  }
};