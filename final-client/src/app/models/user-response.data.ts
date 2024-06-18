import {CarVariantData} from "./car-variant-data";

export interface UserResponseData{
  id: number;
  email: string;
  carVariants: CarVariantData[];
}
