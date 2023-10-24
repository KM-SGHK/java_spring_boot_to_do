import _ from "loadsh";

const initialState = {
  tasks: [],
  loading: null,
  error: null,
};

const taskReducer = (state = initialState, action) => {
  switch (action.type) {
    case "GET_TASKS_REQUESTED":
      return { ...state, loading: true };
    case "GET_TASKS_SUCCESS":
      return {
        ...state,
        loading: false,
        tasks: action.tasks.sort(function (a, b) {
          return a.taskId - b.taskId;
        }),
      };
    case "GET_TASKS_FAILED":
      return { ...state, loading: false, error: action.message };
    case "CREATE_TASKS_SUCCESS":
      console.log("testing action.payload in reducer, ", action.payload);
      return {
        ...state,
        loading: false,
        tasks: [...state.tasks, action.payload],
      };
    case "UPDATE_TASKS_SUCCESS":
      console.log(
        "testing action.payload from Update case in reducer, ",
        action.payload
      );
      return {
        ...state,
        loading: false,
        tasks: [
          ...state.tasks.map((e) => {
            if (e.id == action.payload.id) {
              return action.payload;
            }
            return e;
          }),
        ],
      };
    case "DELETE_TASKS_SUCCESS":
      console.log(
        "testing action.payload from Delete case in reducer, ",
        action.payload,
        state.tasks
      );
      // let selectedIndexForDeletion = _.findIndex(state.tasks, {
      //   id: action.payload,
      // });
      return {
        ...state,
        loading: false,
        tasks: state.tasks.filter(item => {
          console.log("check item, ", item)
          return  item.id !== action.payload
        })
      };
    default:
      return state;
  }
};

export default taskReducer;
