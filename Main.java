package test3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		CategoryAxis x = new CategoryAxis(); // x축 객체 생성
		NumberAxis y = new NumberAxis(); // y축 객체 생성
		x.setLabel("Month"); // x축 이름.
		y.setLabel("Rainfall(ml"); // y축 이름.

		BarChart<String, Number> barChart = new BarChart<String, Number>(x, y); // 메인 BarChart.
		barChart.setTitle("* 2016년 서울시 강수량 분포 *\tThe RainFall Distribution on Seoul in 2016"); 

		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>(); // 강수량 시리즈.
		series1.setName("Rainfall Volume(ml)"); // 시리즈1 이름.   

		// 강수량 시리즈에 데이터 삽입.
		series1.getData().add(new Data<String, Number>("Jan", 1));
		series1.getData().add(new Data<String, Number>("Feb", 47.6));
		series1.getData().add(new Data<String, Number>("Mar", 40.5));
		series1.getData().add(new Data<String, Number>("Apr", 76.8));
		series1.getData().add(new Data<String, Number>("May", 160.5));
		series1.getData().add(new Data<String, Number>("Jun", 54.4));
		series1.getData().add(new Data<String, Number>("Jul", 358.2));
		series1.getData().add(new Data<String, Number>("Aug", 67.1));
		series1.getData().add(new Data<String, Number>("Sep", 33));
		series1.getData().add(new Data<String, Number>("Oct", 74.8));
		series1.getData().add(new Data<String, Number>("Nov", 16.7));
		series1.getData().add(new Data<String, Number>("Dec", 61.1));

		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>(); // 강수 일수 시리즈.
		series2.setName("Rainy Days(day)"); // 시리즈 2 이름.

		// 강수 일 시리즈에 데이터 삽입.
		series2.getData().add(new Data<String, Number>("Jan", 4));
		series2.getData().add(new Data<String, Number>("Feb", 8));
		series2.getData().add(new Data<String, Number>("Mar", 5));
		series2.getData().add(new Data<String, Number>("Apr", 10));
		series2.getData().add(new Data<String, Number>("May", 9));
		series2.getData().add(new Data<String, Number>("Jun", 8));
		series2.getData().add(new Data<String, Number>("Jul", 17));
		series2.getData().add(new Data<String, Number>("Aug", 11));
		series2.getData().add(new Data<String, Number>("Sep", 7));
		series2.getData().add(new Data<String, Number>("Oct", 9));
		series2.getData().add(new Data<String, Number>("Nov", 11));
		series2.getData().add(new Data<String, Number>("Dec", 10));

		Scene scene = new Scene(barChart,800,600);
		barChart.getData().add(series1); // barChart에 시리즈1 추가.
		barChart.getData().add(series2); // barChart에 시리즈2 추가.
		primaryStage.setTitle("[미래웨더] 날씨 통계 데이터 시각화 프로그램 - RainFall Visualizer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
