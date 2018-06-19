import {User} from '../user/user';

export class Reservation {
	id: string
	ownser: User;
	customer: User;
}