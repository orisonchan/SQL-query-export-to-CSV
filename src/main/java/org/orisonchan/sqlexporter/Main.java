package org.orisonchan.sqlexporter;

import org.orisonchan.sqlexporter.service.QueryService;
import org.apache.commons.cli.*;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class Main implements CommandLineRunner {

  @Resource
  private ApplicationContext appContext;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Main.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) {
    Options options = new Options();
    Option oggCmdTask1 = new Option("i", "inputFile", true, "input SQL file absolute path");
    Option oggCmdTask2 = new Option("o", "outputDirectory", true, "CSV output directory");
    oggCmdTask1.setRequired(true);
    oggCmdTask2.setRequired(true);
    options.addOption(oggCmdTask1);
    options.addOption(oggCmdTask2);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("help", options);
      return;
    }

    String params = cmd.getOptionValue("o");

    QueryService queryService = (QueryService) appContext.getBean("queryService");
    queryService.setDirectory(params);

    params = cmd.getOptionValue("i");
    queryService.setInputFile(params);

    queryService.querySQL();

  }

}
