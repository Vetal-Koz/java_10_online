import {Authority} from "./authority.data";

export interface Principal {
  id: number;
  email: string;
  password: string;
  firstName?: string | null;
  lastName?: string | null;
  year?: number | null;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  enabled: boolean;
  carVariants: any[];
  role: string;
  authorities: Authority[];
  username: string;
}
