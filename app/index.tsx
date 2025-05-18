import { createNativeStackNavigator } from "@react-navigation/native-stack";
import StartScreen from "./presentation/screens/start";

const Stack = createNativeStackNavigator();

export default function Index() {
  return (
    <Stack.Navigator initialRouteName="Start" screenOptions={{ headerShown: false }}>
      <Stack.Screen name="Start" component={StartScreen} />
    </Stack.Navigator>
  )
}