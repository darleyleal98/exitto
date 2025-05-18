import { createNativeStackNavigator } from "@react-navigation/native-stack";
import StartScreen from "./presentation/screens/start";
import HomeScreen from "./presentation/screens/home";

const Stack = createNativeStackNavigator();

export default function Index() {
  return (
    <Stack.Navigator initialRouteName="Start" screenOptions={{ headerShown: false }}>
      <Stack.Screen name="Start" component={StartScreen} />
      <Stack.Screen name="Home" component={HomeScreen} />
    </Stack.Navigator>
  )
}