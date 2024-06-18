import {Authority} from "./authority.data";
import {Principal} from "./principal.data";
import {UserDetails} from "./user-details.datal";

export interface User {
  authorities: Authority[];
  details: UserDetails;
  authenticated: boolean;
  principal: Principal;
  credentials: any | null;
  name: string;
}
