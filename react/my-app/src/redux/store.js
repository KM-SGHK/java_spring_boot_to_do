import { combineReducers, createStore, compose, applyMiddleware } from "redux";
import thunk from 'redux-thunk';

import taskReducer from "./tasks/reducer";

const reducers = combineReducers({
  tasks: taskReducer
});

// Interface only for TS
// debugger
// declare global {
//   /* tslint:disable:interface-name */
//   interface Window {
//     __REDUX_DEVTOOLS_EXTENSION_COMPOSE__: any;
//   }
// }

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;




// createStore
export const store = createStore(
  reducers,
  composeEnhancers(applyMiddleware(thunk))
);

